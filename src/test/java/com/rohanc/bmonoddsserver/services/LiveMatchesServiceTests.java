/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rohanc.bmonoddsserver.models.dto.MatchDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDto;
import com.rohanc.bmonoddsserver.repositories.MarketStateRepository;
import com.rohanc.bmonoddsserver.repositories.MatchRepository;
import com.rohanc.bmonoddsserver.repositories.MatchStateRepository;
import com.rohanc.bmonoddsserver.services.aggregate.MatchChildrenAggregate;
import com.rohanc.bmonoddsserver.services.helpers.MatchUpsertHelper;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("integrationtest")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LiveMatchesServiceTests {
  private MatchUpsertDto matchUpsertDto;

  @Autowired private Flyway flyway;

  @BeforeEach
  void setup() {
    matchUpsertDto = MatchUpsertHelper.NewMatchUpsertDto();

    flyway.clean();
    flyway.migrate();
  }

  @Nested
  class MarketCalculations {
    @Autowired private LiveMatchesService liveMatchesService;

    @Test
    void givenNewMatchUpsertDto_createMatchChildrenAggregate_equalFiftyFiftyOdds() {
      MatchChildrenAggregate matchChildrenAggregate =
          liveMatchesService.createMatchChildrenAggregate(matchUpsertDto);
      assertAll(
          "match",
          () -> assertEquals(2F, matchChildrenAggregate.marketStates.get(0).getOdd().floatValue()),
          () -> assertEquals(2F, matchChildrenAggregate.marketStates.get(1).getOdd().floatValue()));
    }

    @Test
    void givenInprogressMatchUpsertDto_createMatchChildrenAggregateMinorLeading_DifferingOdds() {
      matchUpsertDto.getMatchState().setSetScore("1-0");
      MatchChildrenAggregate matchChildrenAggregate =
          liveMatchesService.createMatchChildrenAggregate(matchUpsertDto);
      assertAll(
          "match",
          () ->
              assertEquals(
                  "1.73",
                  String.format("%.2f", matchChildrenAggregate.marketStates.get(0).getOdd())),
          () ->
              assertEquals(
                  "2.30",
                  String.format("%.2f", matchChildrenAggregate.marketStates.get(1).getOdd())));
    }

    @Test
    void givenInprogressMatchUpsertDto_createMatchChildrenAggregateSlightLosing_DifferingOdds() {
      matchUpsertDto.getMatchState().setSetScore("0-2");
      MatchChildrenAggregate matchChildrenAggregate =
          liveMatchesService.createMatchChildrenAggregate(matchUpsertDto);
      assertAll(
          "match",
          () ->
              assertEquals(
                  "2.75",
                  String.format("%.2f", matchChildrenAggregate.marketStates.get(0).getOdd())),
          () ->
              assertEquals(
                  "1.54",
                  String.format("%.2f", matchChildrenAggregate.marketStates.get(1).getOdd())));
    }

    @Test
    void
        givenInprogressMatchUpsertDto_createMatchChildrenAggregateLosingMajorLosing_DifferingOdds() {
      matchUpsertDto.getMatchState().setSetScore("0-6");
      MatchChildrenAggregate matchChildrenAggregate =
          liveMatchesService.createMatchChildrenAggregate(matchUpsertDto);
      assertAll(
          "match",
          () ->
              assertEquals(
                  "12.48",
                  String.format("%.2f", matchChildrenAggregate.marketStates.get(0).getOdd())),
          () ->
              assertEquals(
                  "1.06",
                  String.format("%.2f", matchChildrenAggregate.marketStates.get(1).getOdd())));
    }
  }

  @Nested
  class PersistenceChecks {
    @Autowired LiveMatchesService liveMatchesService;

    @Autowired MatchStateRepository matchStateRepository;

    @Autowired MarketStateRepository marketStateRepository;

    @Autowired MatchRepository matchRepository;

    @Test
    void givenNewMatchUpsertDto_createMatch_matchPersisted() {
      var matchDto = liveMatchesService.createMatch(matchUpsertDto);
      assertTrue(matchDto.getLive());

      var persistedMatch = matchRepository.findById(matchDto.getId()).orElseThrow();
      var persistedMatchState =
          matchStateRepository.findLatestByMatchId(persistedMatch.getId()).orElseThrow();
      var persistedMarketStates =
          marketStateRepository.findLatestByMatchIdOrderByIdDescLimit(persistedMatch.getId(), 2);

      assertNotNull(persistedMatch);
      assertEquals(1L, persistedMatch.getId());
      assertNotNull(persistedMatchState);
      assertEquals(1L, persistedMatchState.getId());
      assertEquals(2, persistedMarketStates.size());
      assertEquals(2L, persistedMarketStates.get(0).getId());
      assertEquals(1L, persistedMarketStates.get(1).getId());
    }

    @Nested
    class PersistedMatch {
      MatchDto persistedMatch;

      @BeforeEach
      void persistNewMatch() {
        persistedMatch = liveMatchesService.createMatch(matchUpsertDto);
        matchUpsertDto.setId(persistedMatch.getId());
      }

      @Test
      void givenExisting00Match_updateNestedElements_matchStatesUpdated() {
        var newPointScore = "15-0";
        matchUpsertDto.getMatchState().setPointScore(newPointScore);

        liveMatchesService.updateMatchAndStates(matchUpsertDto);
        var latestMatchState =
            matchStateRepository.findLatestByMatchId(matchUpsertDto.getId()).orElseThrow();
        var allMarkets = marketStateRepository.findAll();
        var allMatchStates = matchStateRepository.findAll();

        assertEquals(newPointScore, latestMatchState.getPointScore());
        assertEquals(2, allMatchStates.size());
        assertEquals(2, allMarkets.size());
      }

      @Test
      void givenExisting150Match_updateNestedElements_matchStatesUpdated() {
        var newPointScore = "15-0";
        matchUpsertDto.getMatchState().setPointScore(newPointScore);
        liveMatchesService.updateMatchAndStates(matchUpsertDto);

        newPointScore = "30-0";
        var newSetScore = "1-0";
        matchUpsertDto.getMatchState().setPointScore(newPointScore);
        matchUpsertDto.getMatchState().setScore(newSetScore);

        liveMatchesService.updateMatchAndStates(matchUpsertDto);
        var latestMatchState =
            matchStateRepository.findLatestByMatchId(matchUpsertDto.getId()).orElseThrow();
        var allMarkets = marketStateRepository.findAll();
        var allMatchStates = matchStateRepository.findAll();

        assertEquals(newPointScore, latestMatchState.getPointScore());
        assertEquals(3, allMatchStates.size());
        assertEquals(4, allMarkets.size());
      }
    }
  }
}
