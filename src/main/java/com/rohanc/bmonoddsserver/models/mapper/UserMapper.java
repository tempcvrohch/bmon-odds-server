/* (C)2024 */
package com.rohanc.bmonoddsserver.models.mapper;

import com.rohanc.bmonoddsserver.models.db.User;
import com.rohanc.bmonoddsserver.models.dto.UserDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  UserDto toDto(User user);

  @InheritInverseConfiguration
  User fromDto(UserDto userDto);

  List<UserDto> toDtoList(List<User> users);
}
