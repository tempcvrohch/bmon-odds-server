/* (C)2024 */
package com.rohanc.bmonoddsserver.services.util;

public class ScoreParser {
	/**
	 * TODO: should really change the the way these score get handled, old relic
	 * from b365 times.
	 * 
	 * TODO: support multiple sports as in oddsgen
	 *
	 * @param matchState a valid matchState
	 * @return an int array with 2 elements, the leftSideScore and rightSideScore
	 */
	public static int[] ExtractLastSetPlayerScores(String setScore) {
		var splitSetScore = setScore.split(",");

		var splitLastSetScore = splitSetScore[splitSetScore.length - 1].split("-");
		var leftSideScore = Integer.parseInt(splitLastSetScore[0]);
		var rightSideScore = Integer.parseInt(splitLastSetScore[1]);

		return new int[] { leftSideScore, rightSideScore };
	}

	/**
	 * @param leftSideScore  int between 0-7
	 * @param rightSideScore int between 0-7
	 * @return Returns the index of the winning player, or -1 if the match isn't
	 *         finished yet.
	 */
	public static int ParseWinnerIndex(int leftSideScore, int rightSideScore) {
		if (leftSideScore == rightSideScore) {
			return -1;
		} else if (leftSideScore == 7) {
			return 0;
		} else if (rightSideScore == 7) {
			return 1;
		} else {
			return -1;
		}
	}
}
