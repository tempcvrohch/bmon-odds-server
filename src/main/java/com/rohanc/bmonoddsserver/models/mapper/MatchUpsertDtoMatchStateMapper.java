/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.MatchState;
import com.rohanc.bmonoddsserver.models.dto.MatchStateDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDtoMatchState;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MatchUpsertDtoMatchStateMapper {
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "marketStates", ignore = true)
  @Mapping(target = "score", ignore = true)
  MatchStateDto toDto(MatchUpsertDtoMatchState matchUpsertDto);

  @InheritInverseConfiguration
  @Mapping(target = "score", ignore = true)
  MatchUpsertDtoMatchState fromDto(MatchStateDto matchDto);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "match", ignore = true)
  MatchState toDbo(MatchUpsertDtoMatchState matchUpsertDto);

  @InheritInverseConfiguration
  @Mapping(target = "score", ignore = true)
  MatchUpsertDtoMatchState fromDbo(MatchState matchstate);
}
