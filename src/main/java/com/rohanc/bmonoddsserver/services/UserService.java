/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.db.Bet;
import com.rohanc.bmonoddsserver.models.dto.BetDto;
import com.rohanc.bmonoddsserver.models.generic.UserPrincipal;
import com.rohanc.bmonoddsserver.models.mapper.BetMapper;
import com.rohanc.bmonoddsserver.models.response.Session;
import com.rohanc.bmonoddsserver.repositories.BetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired private BetRepository betRepository;

  @Autowired private BetMapper betMapper;

  public List<BetDto> getUserPendingBets(UserPrincipal userPrincipal) {
    var bets = betRepository.findByUserIdLimit(userPrincipal.getUser().getId());
    return betMapper.toDtoList(bets);
  }

  public Session getUserSession(UserPrincipal userPrincipal) {
    var pendingBetsAmount =
        betRepository.countByStatusAndUserId(
            userPrincipal.getUser().getId(), Bet.BetStatus.PENDING.name());
    return new Session(
        userPrincipal.getUsername(), userPrincipal.getUser().getBalance(), pendingBetsAmount);
  }
}
