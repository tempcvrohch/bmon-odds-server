/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.MatchState;
import com.rohanc.bmonoddsserver.models.dto.MatchStateDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MatchStateMapper {
  @Mapping(target = "marketStates", ignore = true)
  MatchStateDto toDto(MatchState matchState);

  @InheritInverseConfiguration
  MatchState fromDto(MatchStateDto matchStateDto);

  List<MatchStateDto> toDtoList(List<MatchState> matchStates);
}
