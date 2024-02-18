/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.Sport;
import com.rohanc.bmonoddsserver.models.dto.SportDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface SportMapper {
  SportDto toDto(Sport sport);

  @InheritInverseConfiguration
  Sport fromDto(SportDto sportDto);

  List<SportDto> toDtoList(List<Sport> sports);
}
