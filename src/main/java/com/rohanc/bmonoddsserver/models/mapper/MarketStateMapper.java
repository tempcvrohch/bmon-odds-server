/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.MarketState;
import com.rohanc.bmonoddsserver.models.dto.MarketStateDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface MarketStateMapper {
  MarketStateDto toDto(MarketState marketState);

  @InheritInverseConfiguration
  MarketState fromDto(MarketStateDto marketStateDto);

  List<MarketStateDto> toDtoList(List<MarketState> marketStates);
}
