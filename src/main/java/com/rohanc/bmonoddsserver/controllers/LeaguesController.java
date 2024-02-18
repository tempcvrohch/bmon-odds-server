/* (C)2024 */
package com.rohanc.bmonoddsserver.controllers;

import com.rohanc.bmonoddsserver.api.LeaguesApi;
import com.rohanc.bmonoddsserver.models.dto.LeagueDto;
import com.rohanc.bmonoddsserver.services.LeagueService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LeaguesController implements LeaguesApi {
  @Autowired private LeagueService leagueService;

  @Override
  public List<LeagueDto> getAllLeagues() throws Exception {
    return leagueService.getAllLeagues();
  }
}
