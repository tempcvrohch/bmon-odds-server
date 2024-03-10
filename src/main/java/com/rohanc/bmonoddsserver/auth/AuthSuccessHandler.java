/* (C)2024 */
package com.rohanc.bmonoddsserver.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

// TODO: supposedly this clears the redirect status, review it
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private RequestCache requestCache = new HttpSessionRequestCache();

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    clearAuthenticationAttributes(request);
    getRedirectStrategy().sendRedirect(request, response, "/api/auth/session");
  }

  public void setRequestCache(RequestCache requestCache) {
    this.requestCache = requestCache;
  }
}
