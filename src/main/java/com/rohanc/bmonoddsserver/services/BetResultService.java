/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.db.Bet;
import com.rohanc.bmonoddsserver.models.db.Bet.BetStatus;
import com.rohanc.bmonoddsserver.models.db.MarketState;
import com.rohanc.bmonoddsserver.models.db.Match;
import com.rohanc.bmonoddsserver.models.db.MatchState;
import com.rohanc.bmonoddsserver.repositories.BetRepository;
import com.rohanc.bmonoddsserver.repositories.MarketStateRepository;
import com.rohanc.bmonoddsserver.repositories.MatchStateRepository;
import com.rohanc.bmonoddsserver.repositories.UserRepository;
import com.rohanc.bmonoddsserver.services.util.ScoreParser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetResultService {
	@Autowired
	private MarketStateRepository marketStateRepository;
	@Autowired
	private MatchStateRepository matchStateRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BetRepository betRepository;

	void processUserBetsOnMatch(Match match) {
		var pendingBets = betRepository.findByMatchId(match.getId());
		if (pendingBets.size() == 0) {
			return;
		}

		var latestMatchState = matchStateRepository.findLatestByMatchId(match.getId()).orElseThrow();
		var latestMarketStates = marketStateRepository.findLatestByMatchIdOrderByIdDescLimit(match.getId(), 2);
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
			processVoidBet(bet);
		} else {
			processFinishedBet(bet, didPlayerWinBet(bet, marketStates, winnerIndex));
		}
	}

	private boolean didPlayerWinBet(Bet bet, List<MarketState> marketStates, int winnerIndex) {
		return bet.getMarketState()
				.getPlayer()
				.getId()
				.equals(marketStates.get(winnerIndex).getPlayer().getId());
	}

	void processFinishedBet(Bet bet, boolean wonBet) {
		BetStatus status = BetStatus.LOSS;
		if (wonBet) {
			status = BetStatus.WIN;
			userRepository.incrementBalanceByUsername(bet.getUser().getId(), bet.getToReturn());
		}

		betRepository.updateOnBetStatusById(bet.getId(), status.name());
	}

	void processVoidBet(Bet bet) {
		userRepository.incrementBalanceByUsername(bet.getUser().getId(), bet.getStake());
		betRepository.updateOnBetStatusById(bet.getId(), Bet.BetStatus.VOID.name());
	}
}
