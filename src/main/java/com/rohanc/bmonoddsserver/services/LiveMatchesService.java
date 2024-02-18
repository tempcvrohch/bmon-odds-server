/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.db.Player;
import com.rohanc.bmonoddsserver.models.dto.MarketStateDto;
import com.rohanc.bmonoddsserver.models.dto.MatchDto;
import com.rohanc.bmonoddsserver.models.dto.MatchStateDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDto;
import com.rohanc.bmonoddsserver.models.mapper.MarketStateMapper;
import com.rohanc.bmonoddsserver.models.mapper.MatchMapper;
import com.rohanc.bmonoddsserver.models.mapper.MatchStateMapper;
import com.rohanc.bmonoddsserver.models.mapper.MatchUpsertDtoMatchStateMapper;
import com.rohanc.bmonoddsserver.models.mapper.MatchUpsertMapper;
import com.rohanc.bmonoddsserver.repositories.MarketRepository;
import com.rohanc.bmonoddsserver.repositories.MarketStateRepository;
import com.rohanc.bmonoddsserver.repositories.MatchRepository;
import com.rohanc.bmonoddsserver.repositories.MatchStateRepository;
import com.rohanc.bmonoddsserver.repositories.PlayerRepository;
import com.rohanc.bmonoddsserver.services.util.MarketCalculator;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LiveMatchesService {
  @Autowired private MatchRepository matchRepository;

  @Autowired private MatchStateRepository matchStateRepository;

  @Autowired private BetResultService betResultService;

  @Autowired private MarketRepository marketRepository;

  @Autowired private MarketStateRepository marketStateRepository;

  @Autowired private PlayerRepository playerRepository;

  @Autowired private MatchMapper matchMapper;

  @Autowired private MatchStateMapper matchStateMapper;

  @Autowired private MarketStateMapper marketStateMapper;

  @Autowired private MatchUpsertMapper matchUpsertMapper;

  @Autowired private MatchUpsertDtoMatchStateMapper matchUpsertDtoMatchStateMapper;

  // TODO: potential threading issues
  private Map<Long, MatchDto> persistedMatchEntities =
      new ConcurrentHashMap<>(); // contains matches of last
  // 24H
  private Map<Long, MatchDto> liveMatchEntities =
      new ConcurrentHashMap<>(); // contains matches of the

  // current updateMatches tick

  public Map<Long, MatchDto> getLiveMatchEntities() {
    return liveMatchEntities;
  }

  public MatchDto createMatch(MatchUpsertDto matchUpsertDto) {
    MatchChildrenAggregate matchChildrenAggregate = createMatchChildrenAggregate(matchUpsertDto);
    return persistNewMatch(matchChildrenAggregate);
  }

  public MatchChildrenAggregate createMatchChildrenAggregate(MatchUpsertDto matchUpsertDto) {
    var match = matchUpsertMapper.toDbo(matchUpsertDto);
    var matchState = matchUpsertDtoMatchStateMapper.toDbo(matchUpsertDto.getMatchState());

    var players = new ArrayList<Player>();
    for (var playerId : matchUpsertDto.getPlayerIds()) {
      var foundPlayer =
          playerRepository.findById(playerId).orElseThrow(MissingPlayerException::new);
      players.add(foundPlayer);
    }

    var market = marketRepository.findById(0L).orElseThrow(MissingMarketException::new);
    var marketStates =
        MarketCalculator.CalculateMarketStatesForMatch(match, matchState, market, players);

    return new MatchChildrenAggregate(match, market, marketStates, matchState, players);
  }

  public void updateMatchAndStates(MatchUpsertDto matchUpsertDto) {
    var matchDto = matchUpsertMapper.toDto(matchUpsertDto);
    if (matchDto.getLive()) {
      updateMatchNestedElements(persistedMatchEntities.get(matchDto.getId()), matchDto);
    } else {
      processFinishedMatch(matchDto);
    }
  }

  private void processFinishedMatch(MatchDto matchDto) {
    persistedMatchEntities.remove(matchDto.getId());
    var match = matchMapper.fromDto(matchDto);
    betResultService.processUserBetsOnMatch(match);
  }

  /**
   * Persist a match and the associated MatchState and MarketStates to the DB and
   * cache list
   *
   * @param matchChildrenAggregate
   *
   * @param matchDto               a match not yet added to the DB
   */
  private MatchDto persistNewMatch(MatchChildrenAggregate matchChildrenAggregate) {
    var insertedMatch = matchRepository.save(matchChildrenAggregate.match);
    matchChildrenAggregate.matchState.setMatch(insertedMatch);

    var insertedMatchState = matchStateRepository.save(matchChildrenAggregate.matchState);
    matchChildrenAggregate.marketStates.forEach(
        marketState -> marketState.setMatchState(insertedMatchState));

    marketStateRepository.saveAll(matchChildrenAggregate.marketStates);

    var matchDto = matchMapper.toDto(matchChildrenAggregate.match);
    return persistedMatchEntities.put(insertedMatch.getId(), matchDto);
  }

  private boolean hasMatchStateChanged(MatchStateDto matchStateA, MatchStateDto matchStateB) {
    return matchStateA.getPointScore() != matchStateA.getPointScore()
        || matchStateA.getSetScore() != matchStateA.getSetScore()
        || matchStateA.getServingIndex() != matchStateA.getServingIndex();
  }

  private boolean hasMarketStateChanged(MarketStateDto marketStateA, MarketStateDto marketStateB) {
    return marketStateA.getOdd() != marketStateB.getOdd()
        || marketStateA.getSuspended() != marketStateB.getSuspended();
  }

  /**
   * Checks if either market states have changed between the current and new match
   * update, if so
   * persist. Checks if the match state has changed between the current and new
   * match update, if so
   * persist.
   * <p>
   * Sets the new match state as the current match state(eg a point score of 0-0
   * -> 0-1).
   * <p>
   * Both params should refer to the same match (eg persistedMatch.name = "PSV vs
   * Ajax" and
   * updatedMatch.name = "PSV vs Ajax").
   *
   * @param persistedMatch current persisted match
   * @param updatedMatch   the updated match
   */
  private void updateMatchNestedElements(MatchDto persistedMatch, MatchDto updatedMatch) {
    boolean hasChanged = false;
    // Check for a point/set/serve index or score difference
    if (hasMatchStateChanged(persistedMatch.getMatchState(), updatedMatch.getMatchState())) {
      var updatedMatchState = matchStateMapper.fromDto(updatedMatch.getMatchState());
      updatedMatchState.getMatch().setId(persistedMatch.getId());

      hasChanged = true;
    }

    if (persistedMatch.getMatchState().getMarketStates() == null
        || hasMarketStateChanged(
            persistedMatch.getMatchState().getMarketStates().get(0),
            updatedMatch.getMatchState().getMarketStates().get(0))) {

      // TODO: technically it could be possible additional markets to be added later
      for (var i = 0; i < updatedMatch.getMatchState().getMarketStates().size(); i++) {
        var upMarketStateDto = updatedMatch.getMatchState().getMarketStates().get(i);
        var marketState = marketStateMapper.fromDto(upMarketStateDto);
        marketState.getMatchState().setId(persistedMatch.getMatchState().getId());

        marketStateRepository.save(marketState);
      }

      hasChanged = true;
    }

    if (hasChanged) {
      updatedMatch.setId(persistedMatch.getId());
      persistedMatchEntities.put(updatedMatch.getId(), updatedMatch);
    }
  }

  public class MissingMarketException extends RuntimeException {}

  public class MissingPlayerException extends RuntimeException {}
}
