/* (C)2024 */
package com.rohanc.bmonoddsserver.controllers;

import com.rohanc.bmonoddsserver.api.PlayersApi;
import com.rohanc.bmonoddsserver.models.dto.PlayerDto;
import com.rohanc.bmonoddsserver.services.PlayersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PlayersController implements PlayersApi {
  @Autowired private PlayersService playersService;

  @Override
  public List<PlayerDto> getAllPlayers() throws Exception {
    return playersService.getAllPlayers();
  }
}
