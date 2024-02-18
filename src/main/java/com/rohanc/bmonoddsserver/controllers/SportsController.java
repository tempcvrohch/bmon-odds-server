/* (C)2024 */
package com.rohanc.bmonoddsserver.controllers;

import com.rohanc.bmonoddsserver.api.SportsApi;
import com.rohanc.bmonoddsserver.models.dto.SportDto;
import com.rohanc.bmonoddsserver.services.SportsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SportsController implements SportsApi {
  @Autowired private SportsService sportsService;

  @Override
  public List<SportDto> getAllSports() throws Exception {
    return sportsService.getAllSports();
  }
}
