/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.Bet;
import com.rohanc.bmonoddsserver.models.dto.BetDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface BetMapper {
  BetDto toDto(Bet bet);

  @InheritInverseConfiguration
  Bet fromDto(BetDto betDto);

  List<BetDto> toDtoList(List<Bet> bets);
}
