/* (C)2024 */
package com.rohanc.bmonoddsserver.models;

import com.rohanc.bmonoddsserver.models.db.User;
import com.rohanc.bmonoddsserver.models.generic.UserPrincipal;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareConfig implements AuditorAware<User> {
  public Optional<User> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }
    var userPrincipal = ((UserPrincipal) authentication.getPrincipal());

    return Optional.of(userPrincipal.getUser());
  }
}
