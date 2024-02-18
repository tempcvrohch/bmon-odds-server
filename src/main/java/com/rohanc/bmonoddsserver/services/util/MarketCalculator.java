/* (C)2024 */
package com.rohanc.bmonoddsserver.services.util;

import com.rohanc.bmonoddsserver.models.db.Market;
import com.rohanc.bmonoddsserver.models.db.MarketState;
import com.rohanc.bmonoddsserver.models.db.Match;
import com.rohanc.bmonoddsserver.models.db.MatchState;
import com.rohanc.bmonoddsserver.models.db.Player;
import java.util.ArrayList;
import java.util.List;

public class MarketCalculator {
  // TODO: Add these to .env vars
  private static final int SET_WINNING_SCORE = 7;
  private static final float BOOKIE_MARGIN = 0.025f;
  private static final float STAKE_LIMIT = 100;

  public static List<MarketState> CalculateMarketStatesForMatch(
      Match match, MatchState matchState, Market market, List<Player> players) {
    var setScore = matchState.getSetScore();

    var oddArray = new float[] {2f, 2f};
    if (setScore != "0-0") {
      var setScoreArray = ScoreParser.ExtractLastSetPlayerScores(setScore);
      // TODO: should really also look at point score instead of just set score
      var scoreDiff = setScoreArray[0] - setScoreArray[1];

      // TODO: should really be using bigdecimal here
      // calculate the differential in scores, the bigger the diff the higher the odds
      // of winning
      var chanceDiff = 50 + (scoreDiff * (50 / SET_WINNING_SCORE));
      var chanceArray = new float[] {50F, 50F};
      if (chanceDiff < 0) {
        chanceArray[0] = (100F - chanceDiff) / 100F;
        chanceArray[1] = chanceDiff / 100F;
      } else {
        chanceArray[0] = (Math.abs(chanceDiff) / 100F);
        chanceArray[1] = ((100F - Math.abs(chanceDiff)) / 100F);
      }

      // The higher the odds of winning, the lower the payout
      oddArray[0] = (1 / chanceArray[0]) - BOOKIE_MARGIN;
      oddArray[1] = (1 / chanceArray[1]) - BOOKIE_MARGIN;
    }

    var marketStates = new ArrayList<MarketState>();
    for (var i = 0; i < oddArray.length; i++) {
      var marketState = new MarketState(oddArray[i]);
      marketState.setStakeLimit(STAKE_LIMIT);
      marketState.setPlayer(players.get(i));
      marketState.setMarket(market);
      marketState.setMatchState(matchState);

      marketStates.add(marketState);
    }

    return marketStates;
  }

  public class InvalidPlayerAmountException extends Exception {
    public InvalidPlayerAmountException(String message) {}
    ;
  }
}
