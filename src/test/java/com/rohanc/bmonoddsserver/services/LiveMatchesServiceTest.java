/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rohanc.bmonoddsserver.models.db.Market;
import com.rohanc.bmonoddsserver.models.db.Player;
import com.rohanc.bmonoddsserver.models.dto.LeagueDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDtoMatchState;
import com.rohanc.bmonoddsserver.models.dto.PlayerDto;
import com.rohanc.bmonoddsserver.models.dto.SportDto;
import com.rohanc.bmonoddsserver.models.mapper.PlayerMapper;
import com.rohanc.bmonoddsserver.repositories.MarketRepository;
import com.rohanc.bmonoddsserver.repositories.PlayerRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class LiveMatchesServiceTest {
  private Player player1;
  private Player player2;
  private ArrayList<Player> players;
  private Market market;
  private PlayerDto playerDto1;
  private PlayerDto playerDto2;
  private LeagueDto leagueDto;
  private SportDto sportDto;
  private MatchUpsertDtoMatchState matchUpsertDtoMatchState;
  private MatchUpsertDto matchUpsertDto;

  @BeforeEach
  void prepareMatchProperties() {
    player1 = new Player("Novak", "Djokovic", "novak-djokovic", "CR");
    player1.setId(0L);
    player2 = new Player("Carlos", "Alcaraz", "carlos-alcaraz", "SP");
    player2.setId(1L);
    players = new ArrayList<>();
    players.add(player1);
    players.add(player2);

    market = new Market("Fulltime Result");

    leagueDto = new LeagueDto(-1L, "ATP 2024");
    sportDto = new SportDto(-1L, "Tennis");

    matchUpsertDtoMatchState = new MatchUpsertDtoMatchState();
    matchUpsertDtoMatchState.setPointScore("0-0");
    matchUpsertDtoMatchState.setScore("0-0");
    matchUpsertDtoMatchState.setServingIndex(0);

    var playerIds = new ArrayList<Long>();
    playerIds.add(0L);
    playerIds.add(1L);

    var matchName = String.format("%s vs %s", player1.getFirstname(), player2.getFirstname());
    matchUpsertDto =
        new MatchUpsertDto(
            matchName, true, leagueDto, sportDto, playerIds, matchUpsertDtoMatchState);
  }

  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  class MarketCalculations {
    @Autowired @InjectMocks private LiveMatchesService liveMatchesService;

    @Autowired private PlayerMapper playerMapper;

    @MockBean private PlayerRepository playerRepository;

    @MockBean private MarketRepository marketRepository;

    @BeforeEach
    void setupMappers() {
      playerDto1 = playerMapper.toDto(player1);
      playerDto2 = playerMapper.toDto(player2);
    }

    @BeforeEach
    void setupMockRepositories() {
      for (var player : players) {
        Mockito.when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
      }

      Mockito.when(marketRepository.findById(0L)).thenReturn(Optional.of(market));
    }

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

  @DataJpaTest
  @Nested
  @ActiveProfiles("integrationtest")
  class PersistenceChecks {

    @Autowired private PlayerRepository playerRepository;

    @Test
    void aaa() {
      playerRepository.save(player1);
      var a = playerRepository.findById(0L);
      assertNotNull(a);
    } //

    @Test
    void bbb() {
      // playerRepository.save(player1);
      var a = playerRepository.findById(0L);
      assertNotNull(a.get());
    }
  }
}
