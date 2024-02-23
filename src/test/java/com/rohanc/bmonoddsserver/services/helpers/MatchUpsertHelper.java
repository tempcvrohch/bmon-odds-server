/* (C)2024 */
package com.rohanc.bmonoddsserver.services.helpers;

import com.rohanc.bmonoddsserver.models.dto.LeagueDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDtoMatchState;
import com.rohanc.bmonoddsserver.models.dto.SportDto;
import java.util.ArrayList;

public class MatchUpsertHelper {
  public static MatchUpsertDto NewMatchUpsertDto() {
    var leagueDto = new LeagueDto(1L, "ATP 2024");
    var sportDto = new SportDto(1L, "Tennis");

    var matchUpsertDtoMatchState = new MatchUpsertDtoMatchState();
    matchUpsertDtoMatchState.setPointScore("0-0");
    matchUpsertDtoMatchState.setScore("0-0");
    matchUpsertDtoMatchState.setServingIndex(0);

    var playerIds = new ArrayList<Long>();
    playerIds.add(1L);
    playerIds.add(2L);

    var matchUpsertDto =
        new MatchUpsertDto(
            "Random #1 vs Random #2",
            true,
            leagueDto,
            sportDto,
            playerIds,
            matchUpsertDtoMatchState);
    matchUpsertDto.setLive(true);
    return matchUpsertDto;
  }
}
