/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.db.Bet;
import com.rohanc.bmonoddsserver.models.db.MarketState;
import com.rohanc.bmonoddsserver.models.db.Match;
import com.rohanc.bmonoddsserver.models.db.MatchState;
import com.rohanc.bmonoddsserver.repositories.BetRepository;
import com.rohanc.bmonoddsserver.repositories.MarketStateRepository;
import com.rohanc.bmonoddsserver.repositories.MatchStateRepository;
import com.rohanc.bmonoddsserver.services.util.ScoreParser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetResultService {
  @Autowired private MarketStateRepository marketStateRepository;
  @Autowired private MatchStateRepository matchStateRepository;

  @Autowired private BetRepository betRepository;

  @Autowired private BetService betService;

  void processUserBetsOnMatch(Match match) {
    var pendingBets = betRepository.findByMatchId(match.getId());
    if (pendingBets.size() == 0) {
      return;
    }

    var latestMatchState = matchStateRepository.findLatestByMatchId(match.getId());
    var latestMarketStates =
        marketStateRepository.findLatestByMatchIdOrderByIdDescLimit(match.getId(), 2);
    var sideScoresArray = ScoreParser.ExtractLastSetPlayerScores(latestMatchState.getSetScore());
    var winnerIndex = ScoreParser.ParseWinnerIndex(sideScoresArray[0], sideScoresArray[1]);

    pendingBets.forEach(
        bet -> {
          processFinishedMatchBet(latestMatchState, latestMarketStates, bet, winnerIndex);
        });
  }

  private void processFinishedMatchBet(
      MatchState matchState, List<MarketState> marketStates, Bet bet, int winnerIndex) {
    if (winnerIndex == -1) {
      betService.processVoidBet(bet);
    } else {
      betService.processFinishedBet(bet, didPlayerWinBet(bet, marketStates, winnerIndex));
    }
  }

  private boolean didPlayerWinBet(Bet bet, List<MarketState> marketStates, int winnerIndex) {
    return bet.getMarketState()
        .getPlayer()
        .getId()
        .equals(marketStates.get(winnerIndex).getPlayer().getId());
  }
}
