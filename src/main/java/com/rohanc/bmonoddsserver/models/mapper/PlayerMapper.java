/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.Player;
import com.rohanc.bmonoddsserver.models.dto.PlayerDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface PlayerMapper {
  PlayerDto toDto(Player player);

  @InheritInverseConfiguration
  Player fromDto(PlayerDto playerDto);

  List<PlayerDto> toDtoList(List<Player> players);
}
