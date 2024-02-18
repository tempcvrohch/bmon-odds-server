/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.Match;
import com.rohanc.bmonoddsserver.models.dto.MatchDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface MatchMapper {
  MatchDto toDto(Match match);

  @InheritInverseConfiguration
  Match fromDto(MatchDto matchDto);

  List<MatchDto> toDtoList(List<Match> matchs);
}
