/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rohanc.bmonoddsserver.models.db.Bet.BetStatus;
import com.rohanc.bmonoddsserver.models.db.User;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDto;
import com.rohanc.bmonoddsserver.models.dto.UserDto;
import com.rohanc.bmonoddsserver.models.mapper.UserMapper;
import com.rohanc.bmonoddsserver.repositories.BetRepository;
import com.rohanc.bmonoddsserver.repositories.UserRepository;
import com.rohanc.bmonoddsserver.services.helpers.MatchUpsertHelper;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationtest")
public class BetServiceTests {
  @Autowired UserService userService;

  @Autowired UserCoreService userCoreService;

  @Autowired LiveMatchesService liveMatchesService;

  @Autowired BetService betService;

  @Autowired UserMapper userMapper;

  @Autowired UserRepository userRepository;

  @Autowired BetRepository betRepository;

  @Autowired Flyway flyway;

  UserDto userDto;
  MatchUpsertDto matchUpsertDto;
  User user;
  Float stake;

  @BeforeEach
  void setup() {
    flyway.clean();
    flyway.migrate();

    userDto = userCoreService.register("test_buddy", "secret");
    user = userMapper.fromDto(userDto);
    matchUpsertDto = MatchUpsertHelper.NewMatchUpsertDto();
    var insertedMatchDto = liveMatchesService.createMatch(matchUpsertDto);

    matchUpsertDto.setId(insertedMatchDto.getId());
    stake = 10F;
  }

  @Test
  void givenExistingMatchAndUser_addBet_persistedBet() {
    var betDto = betService.addBet(user, stake, 1L);
    assertEquals(1L, betDto.getId());

    var persistedUser = userRepository.findById(user.getId()).orElseThrow();
    assertEquals(990F, persistedUser.getBalance());
  }

  @Test
  void givenPlacedBetAndFinishedMatch_processUserBetsOnMatch_userWonBet() {
    betService.addBet(user, stake, 1L);

    matchUpsertDto.getMatchState().setScore("7-5,7-4,7-3");
    matchUpsertDto.getMatchState().setPointScore("40-0");
    matchUpsertDto.setLive(false);

    liveMatchesService.updateMatchAndStates(matchUpsertDto);
    var persistedBet = betRepository.findById(1L).orElseThrow();
    var persistedUser = userRepository.findById(1L).orElseThrow();

    assertEquals(1010F, persistedUser.getBalance());
    assertEquals(persistedBet.getStatus(), BetStatus.WIN);
  }

  @Test
  void givenPlacedBetAndFinishedMatch_processUserBetsOnMatch_userLostBet() {
    betService.addBet(user, stake, 2L);

    matchUpsertDto.getMatchState().setScore("7-5,7-4,7-3");
    matchUpsertDto.getMatchState().setPointScore("40-0");
    matchUpsertDto.setLive(false);

    liveMatchesService.updateMatchAndStates(matchUpsertDto);
    var persistedBet = betRepository.findById(1L).orElseThrow();
    var persistedUser = userRepository.findById(1L).orElseThrow();

    assertEquals(990F, persistedUser.getBalance());
    assertEquals(persistedBet.getStatus(), BetStatus.LOSS);
  }

  @Test
  void givenPlacedBetAndFinishedMatchInvalidScore_processUserBetsOnMatch_userVoidedBet() {
    betService.addBet(user, stake, 2L);

    matchUpsertDto.getMatchState().setScore("7-5,7-4,7-7");
    matchUpsertDto.getMatchState().setPointScore("40-0");
    matchUpsertDto.setLive(false);

    liveMatchesService.updateMatchAndStates(matchUpsertDto);
    var persistedBet = betRepository.findById(1L).orElseThrow();
    var persistedUser = userRepository.findById(1L).orElseThrow();

    assertEquals(1000F, persistedUser.getBalance());
    assertEquals(persistedBet.getStatus(), BetStatus.VOID);
  }
}
