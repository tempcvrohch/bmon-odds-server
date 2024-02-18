/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.dto.SportDto;
import com.rohanc.bmonoddsserver.models.mapper.SportMapper;
import com.rohanc.bmonoddsserver.repositories.SportRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportsService {
  @Autowired private SportRepository sportRepository;

  @Autowired private SportMapper sportMapper;

  public List<SportDto> getAllSports() {
    var sports = sportRepository.findAll();
    return sportMapper.toDtoList(sports);
  }
}
