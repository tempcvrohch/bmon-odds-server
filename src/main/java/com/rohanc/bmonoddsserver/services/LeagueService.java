/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.dto.LeagueDto;
import com.rohanc.bmonoddsserver.models.mapper.LeagueMapper;
import com.rohanc.bmonoddsserver.repositories.LeagueRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {
  @Autowired private LeagueRepository leagueRepository;

  @Autowired private LeagueMapper leagueMapper;

  public List<LeagueDto> getAllLeagues() {
    var leagues = leagueRepository.findAll();
    return leagueMapper.toDtoList(leagues);
  }
}
