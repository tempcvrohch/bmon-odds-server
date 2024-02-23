/* (C)2024 */
package com.rohanc.bmonoddsserver.controllers;

import com.rohanc.bmonoddsserver.api.MatchApi;
import com.rohanc.bmonoddsserver.models.dto.MarketStateDto;
import com.rohanc.bmonoddsserver.models.dto.MatchDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDto;
import com.rohanc.bmonoddsserver.services.LiveMatchesService;
import com.rohanc.bmonoddsserver.services.MatchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MatchController implements MatchApi {
	@Autowired
	private MatchService matchService;

	@Autowired
	private LiveMatchesService liveMatchesService;

	@Override
	public List<MarketStateDto> getLatestMarketsByMatchId(Long id) throws Exception {
		return matchService.getLatestMarketByMatchId(id);
	}

	@Override
	public MatchDto getMatchById(Long id) throws Exception {
		return matchService.getMatchById(id);
	}

	@Override
	public MatchDto createMatch(@NotNull String X_XSRF_TOKEN, @Valid MatchUpsertDto matchUpsertDto) throws Exception {
		return liveMatchesService.createMatch(matchUpsertDto);
	}

	@Override
	public void updateMatchAndStates(@NotNull String X_XSRF_TOKEN, Long id, @Valid MatchUpsertDto matchUpsertDto)
			throws Exception {
		liveMatchesService.updateMatchAndStates(matchUpsertDto);
	}
}
