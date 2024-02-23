package com.rohanc.bmonoddsserver.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Configuration
public class ServletConfig {
	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return new ServletContextInitializer() {

			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				// servletContext.getSessionCookieConfig().setSecure(true);
			}
		};
	}
}
