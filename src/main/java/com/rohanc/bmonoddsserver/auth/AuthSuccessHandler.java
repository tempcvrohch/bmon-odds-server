/* (C)2024 */
package com.rohanc.bmonoddsserver.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

// TODO: supposedly this clears the redirect status, review it
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private RequestCache requestCache = new HttpSessionRequestCache();

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    SavedRequest savedRequest = requestCache.getRequest(request, response);
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
    if (savedRequest == null) {
      clearAuthenticationAttributes(request);
      return;
    }

    String targetUrlParam = getTargetUrlParameter();
    if (isAlwaysUseDefaultTargetUrl()
        || (targetUrlParam != null && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
      requestCache.removeRequest(request, response);
      clearAuthenticationAttributes(request);
      return;
    }

    clearAuthenticationAttributes(request);

    // CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    // response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
  }

  public void setRequestCache(RequestCache requestCache) {
    this.requestCache = requestCache;
  }
}
