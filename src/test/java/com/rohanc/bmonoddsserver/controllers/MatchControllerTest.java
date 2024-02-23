package com.rohanc.bmonoddsserver.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDto;
import com.rohanc.bmonoddsserver.services.LiveMatchesService;
import com.rohanc.bmonoddsserver.services.MatchService;
import com.rohanc.bmonoddsserver.services.helpers.MatchUpsertHelper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MatchController.class)
@ActiveProfiles("integrationtest")
@TestInstance(Lifecycle.PER_CLASS)
public class MatchControllerTest {
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@MockBean
	MatchService matchService;

	@MockBean
	LiveMatchesService liveMatchesService;

	MatchUpsertDto matchUpsertDto;

	@BeforeAll
	void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@BeforeEach
	void setupEach() {
		matchUpsertDto = MatchUpsertHelper.NewMatchUpsertDto();
	}

	@Test
	void givenNone_createMatch_matchInserted() throws Exception {
		// verify(liveMatchesService.createMatch(any(MatchUpsertDto.class)));
		var objectMapper = new ObjectMapper();
		var body = objectMapper.writeValueAsString(matchUpsertDto);

		var req = MockMvcRequestBuilders.post("/match").contentType(MediaType.APPLICATION_JSON).content(body)
				.with(SecurityMockMvcRequestPostProcessors.csrf());
		mockMvc.perform(req)
				.andExpect(result -> assertEquals(200, result.getResponse().getStatus()));
		verify(liveMatchesService).createMatch(argThat(dto -> dto.getLeague().getId() == 1L));
	}
}
