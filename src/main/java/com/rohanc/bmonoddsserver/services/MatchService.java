/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.db.Match;
import com.rohanc.bmonoddsserver.models.dto.MarketStateDto;
import com.rohanc.bmonoddsserver.models.dto.MatchDto;
import com.rohanc.bmonoddsserver.models.mapper.MarketStateMapper;
import com.rohanc.bmonoddsserver.models.mapper.MatchMapper;
import com.rohanc.bmonoddsserver.models.mapper.MatchStateMapper;
import com.rohanc.bmonoddsserver.repositories.MarketStateRepository;
import com.rohanc.bmonoddsserver.repositories.MatchRepository;
import com.rohanc.bmonoddsserver.repositories.MatchStateRepository;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
  @Autowired private MatchRepository matchRepository;

  @Autowired private MatchStateRepository matchStateRepository;

  @Autowired private MarketStateRepository marketStateRepository;

  @Autowired private LiveMatchesService liveMatchesService;

  @Autowired private MatchMapper matchMapper;

  @Autowired private MatchStateMapper matchStateMapper;

  @Autowired private MarketStateMapper marketStateMapper;

  private final int DEFAULT_MARKET_SIZE = 2;

  public List<Match> getMatches() {
    return matchRepository.findAll();
  }

  public List<MatchDto> getMatchesBetweenDates(OffsetDateTime from, OffsetDateTime to) {
    var listMatches =
        matchRepository.findBetweenTimestamps(
            Timestamp.from(from.toInstant()), Timestamp.from(to.toInstant()));

    return matchMapper.toDtoList(listMatches);
  }

  public MatchDto getMatchById(Long id) {
    var optionalMatch = matchRepository.findById(id);
    var match = optionalMatch.orElseThrow(MatchNotFound::new);

    var matchState = matchStateRepository.findLatestByMatchId(id);
    var marketStates =
        marketStateRepository.findLatestByMatchIdOrderByIdDescLimit(id, DEFAULT_MARKET_SIZE);

    var matchDto = matchMapper.toDto(match);
    var matchStateDto = matchStateMapper.toDto(matchState);
    var marketStatesDtoList = marketStateMapper.toDtoList(marketStates);

    matchStateDto.setMarketStates(marketStatesDtoList);
    matchDto.setMatchState(matchStateDto);
    return matchDto;
  }

  public List<MarketStateDto> getLatestMarketByMatchId(Long id) {
    var marketStates =
        marketStateRepository.findLatestByMatchIdOrderByIdDescLimit(id, DEFAULT_MARKET_SIZE);
    return marketStateMapper.toDtoList(marketStates);
  }

  public List<MatchDto> getRecentMatches() {
    var calendar = new GregorianCalendar();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    var matches = matchRepository.findAfterTimestamp(new Timestamp(calendar.getTimeInMillis()));
    var matchDtoList = matchMapper.toDtoList(matches);

    matchDtoList.forEach(
        match ->
            match.setLive(liveMatchesService.getLiveMatchEntities().containsKey(match.getId())));

    return matchDtoList;
  }

  public class MatchNotFound extends RuntimeException {}
}
