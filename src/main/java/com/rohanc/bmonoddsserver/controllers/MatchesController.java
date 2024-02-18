/* (C)2024 */
package com.rohanc.bmonoddsserver.controllers;

import com.rohanc.bmonoddsserver.api.MatchesApi;
import com.rohanc.bmonoddsserver.models.dto.MatchDto;
import com.rohanc.bmonoddsserver.services.MatchService;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MatchesController implements MatchesApi {
  @Autowired private MatchService matchService;

  @Override
  public List<MatchDto> getMatches(@Valid OffsetDateTime from, @Valid OffsetDateTime to)
      throws Exception {
    return matchService.getMatchesBetweenDates(from, to);
  }

  @Override
  public List<MatchDto> getRecentMatches() throws Exception {
    return matchService.getRecentMatches();
  }
}
