/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.dto.PlayerDto;
import com.rohanc.bmonoddsserver.models.mapper.PlayerMapper;
import com.rohanc.bmonoddsserver.repositories.PlayerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayersService {
  @Autowired private PlayerRepository playerRepository;
  @Autowired private PlayerMapper playerMapper;

  public List<PlayerDto> getAllPlayers() {
    var players = playerRepository.findAll();
    return playerMapper.toDtoList(players);
  }
}
