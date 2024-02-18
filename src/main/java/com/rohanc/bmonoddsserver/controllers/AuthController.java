/* (C)2024 */
package com.rohanc.bmonoddsserver.controllers;

import com.rohanc.bmonoddsserver.api.AuthApi;
import com.rohanc.bmonoddsserver.models.dto.UserDto;
import com.rohanc.bmonoddsserver.models.dto.UserRegisterDto;
import com.rohanc.bmonoddsserver.models.generic.UserPrincipal;
import com.rohanc.bmonoddsserver.models.mapper.UserMapper;
import com.rohanc.bmonoddsserver.services.UserCoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController implements AuthApi {
  @Autowired private UserCoreService userCoreService;

  @Autowired private UserMapper userMapper;

  @Override
  public UserDto register(@Valid UserRegisterDto userRegisterDto) throws Exception {
    return userCoreService.register(userRegisterDto.getUsername(), userRegisterDto.getPassword());
  }

  @Override
  public UserDto getUserSession() throws Exception {
    UserPrincipal userPrincipal =
        (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (userPrincipal == null) {
      throw new NotLoggedInException();
    }

    return userMapper.toDto(userPrincipal.getUser());
  }

  public class NotLoggedInException extends RuntimeException {}
}
