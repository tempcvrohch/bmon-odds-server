/* (C)2024 */
package com.rohanc.bmonoddsserver.controllers;

import com.rohanc.bmonoddsserver.api.UserApi;
import com.rohanc.bmonoddsserver.models.dto.BetDto;
import com.rohanc.bmonoddsserver.models.generic.UserPrincipal;
import com.rohanc.bmonoddsserver.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController implements UserApi {
  @Autowired private UserService userService;

  @Override
  public List<BetDto> getUserBetsPending() throws Exception {
    UserPrincipal userPrincipal =
        (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return userService.getUserPendingBets(userPrincipal);
  }
}
