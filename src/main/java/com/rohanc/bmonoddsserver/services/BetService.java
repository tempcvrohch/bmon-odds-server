/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.db.Bet;
import com.rohanc.bmonoddsserver.models.db.Bet.BetStatus;
import com.rohanc.bmonoddsserver.models.db.User;
import com.rohanc.bmonoddsserver.models.dto.BetDto;
import com.rohanc.bmonoddsserver.models.mapper.BetMapper;
import com.rohanc.bmonoddsserver.repositories.BetRepository;
import com.rohanc.bmonoddsserver.repositories.MarketStateRepository;
import com.rohanc.bmonoddsserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BetService {
  @Autowired private BetRepository betRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private MarketStateRepository marketStateRepository;

  @Autowired private BetMapper betMapper;

  @Transactional
  public BetDto addBet(User user, float stake, long marketStateId) {
    if (user.getBalance() < stake) {
      throw new InsufficientBalanceException();
    }

    if (stake < 0.25 || stake > 100) {
      throw new StakeOutOfBoundsException();
    }

    var previousBet = betRepository.findByMarketStateIdAndUserId(marketStateId, user.getId());
    if (previousBet != null) {
      throw new BetAlreadyPlacedException();
    }

    var marketState =
        marketStateRepository
            .findById(marketStateId)
            .orElseThrow(UnknownMarketStateOnBetException::new);

    // TODO: this bookie is too lenient, check if targetMarketState is the latest
    // TODO: also check if the match has already ended
    var bet = new Bet(BetStatus.PENDING, stake, stake * marketState.getOdd());
    bet.setUser(user);
    bet.setMarketState(marketState);

    var placedBet = betRepository.save(bet);
    userRepository.reduceBalanceByUsername(user.getId(), stake);
    return betMapper.toDto(placedBet);
  }

  void processFinishedBet(Bet bet, boolean wonBet) {
    BetStatus status = BetStatus.LOSS;
    if (wonBet) {
      status = BetStatus.WIN;
      userRepository.incrementBalanceByUsername(bet.getUser().getId(), bet.getToReturn());
    }

    betRepository.updateOnBetStatusById(bet.getUser().getId(), status); // .name()
  }

  void processVoidBet(Bet bet) {
    userRepository.incrementBalanceByUsername(bet.getUser().getId(), bet.getStake());
    betRepository.updateOnBetStatusById(bet.getUser().getId(), Bet.BetStatus.VOID); // .name()
  }

  // TODO: perhaps create a AddBetException, all of these cause a 400 anyway
  public class InsufficientBalanceException extends RuntimeException {}

  public class BetAlreadyPlacedException extends RuntimeException {}

  public class UnknownMarketStateOnBetException extends RuntimeException {}

  public class StakeOutOfBoundsException extends RuntimeException {}

  public class InvalidFractionalOddException extends RuntimeException {
    InvalidFractionalOddException(String s) {
      super(s);
    }
  }
}
