/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.Match;
import com.rohanc.bmonoddsserver.models.dto.MatchDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MatchUpsertMapper {
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "live", ignore = true)
  @Mapping(target = "matchState", ignore = true)
  @Mapping(target = "matchStates", ignore = true)
  MatchDto toDto(MatchUpsertDto matchUpsertDto);

  @InheritInverseConfiguration
  @Mapping(target = "playerIds", ignore = true)
  MatchUpsertDto fromDto(MatchDto matchDto);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  Match toDbo(MatchUpsertDto matchUpsertDto);

  @InheritInverseConfiguration
  @Mapping(target = "live", ignore = true)
  @Mapping(target = "matchState", ignore = true)
  @Mapping(target = "playerIds", ignore = true)
  MatchUpsertDto fromDbo(Match match);
}
