/* (C)2024 */
package com.rohanc.bmonoddsserver.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Component
public class RequestBodyFilter implements Filter {
  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    ContentCachingRequestWrapper contentCachingRequestWrapper =
        new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);

    filterChain.doFilter(contentCachingRequestWrapper, servletResponse);
  }
}
