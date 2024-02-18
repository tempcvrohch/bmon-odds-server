/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.League;
import com.rohanc.bmonoddsserver.models.dto.LeagueDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface LeagueMapper {
  LeagueDto toDto(League league);

  @InheritInverseConfiguration
  League fromDto(LeagueDto leagueDto);

  List<LeagueDto> toDtoList(List<League> leagues);
}
