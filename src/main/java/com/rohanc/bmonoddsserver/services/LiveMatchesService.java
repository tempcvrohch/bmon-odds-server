/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.db.MarketState;
import com.rohanc.bmonoddsserver.models.db.MatchState;
import com.rohanc.bmonoddsserver.models.db.Player;
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
import com.rohanc.bmonoddsserver.services.aggregate.MatchChildrenAggregate;
import com.rohanc.bmonoddsserver.services.util.MarketCalculator;
import java.util.*;
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

    // TODO: fix this hardcoded id
    var market = marketRepository.findById(1L).orElseThrow(MissingMarketException::new);
    var marketStates =
        MarketCalculator.CalculateMarketStatesForMatch(match, matchState, market, players);

    return new MatchChildrenAggregate(match, market, marketStates, matchState, players);
  }

  public void updateMatchAndStates(MatchUpsertDto matchUpsertDto) {
    var matchDto = matchUpsertMapper.toDto(matchUpsertDto);
    MatchChildrenAggregate matchChildrenAggregate = createMatchChildrenAggregate(matchUpsertDto);
    updateMatchNestedElements(matchDto, matchChildrenAggregate);

    if (!matchDto.getLive()) {
      matchRepository.setLiveStatus(matchUpsertDto.getId(), false);
      betResultService.processUserBetsOnMatch(matchChildrenAggregate.getMatch());
    }
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

    // Set parent
    matchChildrenAggregate.matchState.setMatch(insertedMatch);
    var insertedMatchState = matchStateRepository.save(matchChildrenAggregate.matchState);

    // Set parents
    matchChildrenAggregate.marketStates.forEach(
        marketState -> marketState.setMatchState(insertedMatchState));

    var marketStates = marketStateRepository.saveAll(matchChildrenAggregate.marketStates);

    var matchDto = matchMapper.toDto(matchChildrenAggregate.match);
    var matchStateDto = matchStateMapper.toDto(insertedMatchState);
    var marketStateDtos = marketStateMapper.toDtoList(marketStates);

    matchStateDto.addMarketStatesItem(marketStateDtos.get(0));
    matchStateDto.addMarketStatesItem(marketStateDtos.get(1));
    matchDto.setMatchState(matchStateDto);
    matchDto.setLive(true);

    return matchDto;
  }

  private boolean hasMatchStateChanged(
      MatchState persistedMatchState, MatchStateDto updatedMatchState) {
    return persistedMatchState.getPointScore() != updatedMatchState.getPointScore()
        || persistedMatchState.getSetScore() != updatedMatchState.getSetScore()
        || persistedMatchState.getServingIndex() != updatedMatchState.getServingIndex();
  }

  private boolean hasMarketStateChanged(MarketState marketState, MarketState marketState2) {
    return Math.abs(marketState.getOdd() - marketState2.getOdd()) > 0.01
        || marketState.isSuspended() != marketState2.isSuspended();
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
   * @param persistedMatch         current persisted match
   * @param updatedMatch           the updated match
   * @param matchChildrenAggregate
   */
  private void updateMatchNestedElements(
      MatchDto updatedMatch, MatchChildrenAggregate matchChildrenAggregate) {
    var persistedMatch =
        matchRepository.findById(updatedMatch.getId()).orElseThrow(MissingMatchException::new);
    var persistedMatchState =
        matchStateRepository
            .findLatestByMatchId(updatedMatch.getId())
            .orElseThrow(MissingMatchStateException::new);

    // Check for a point/set/serve index or score difference
    if (hasMatchStateChanged(persistedMatchState, updatedMatch.getMatchState())) {

      var updatedMatchState = matchStateMapper.fromDto(updatedMatch.getMatchState());
      // Set parent
      updatedMatchState.setMatch(persistedMatch);

      var newMatchState = matchStateRepository.save(updatedMatchState);

      var persistedMarketStates =
          marketStateRepository.findLatestByMatchIdOrderByIdDescLimit(
              updatedMatch.getId(), matchChildrenAggregate.getMarketStates().size());

      // If the match state has changed, the marketstates should also have been
      // changed.
      if (hasMarketStateChanged(
          persistedMarketStates.get(0), matchChildrenAggregate.getMarketStates().get(0))) {

        for (var i = 0; i < matchChildrenAggregate.getMarketStates().size(); i++) {
          var upMarketState = matchChildrenAggregate.getMarketStates().get(i);

          // Set parents
          upMarketState.setMatchState(newMatchState);
          upMarketState.setMarket(persistedMarketStates.get(i).getMarket());
          upMarketState.setPlayer(persistedMarketStates.get(i).getPlayer());

          marketStateRepository.save(upMarketState);
        }
      }
    }
  }

  public class MissingMatchException extends RuntimeException {}

  public class MissingMatchStateException extends RuntimeException {}

  public class MissingMarketException extends RuntimeException {}

  public class MissingPlayerException extends RuntimeException {}
}
