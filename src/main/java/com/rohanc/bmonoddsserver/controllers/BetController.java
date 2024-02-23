/* (C)2024 */
package com.rohanc.bmonoddsserver.controllers;

import com.rohanc.bmonoddsserver.api.BetApi;
import com.rohanc.bmonoddsserver.models.dto.BetDto;
import com.rohanc.bmonoddsserver.models.dto.BetPlaceDto;
import com.rohanc.bmonoddsserver.models.generic.UserPrincipal;
import com.rohanc.bmonoddsserver.services.BetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BetController implements BetApi {
	@Autowired
	private BetService betService;

	@Override
	public BetDto placeBet(@NotNull String X_XSRF_TOKEN, Long marketId, @Valid BetPlaceDto placeRequestDto)
			throws Exception {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return betService.addBet(userPrincipal.getUser(), placeRequestDto.getStake(), marketId);
	}
}
