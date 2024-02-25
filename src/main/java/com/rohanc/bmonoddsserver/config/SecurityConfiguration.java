/* (C)2024 */
package com.rohanc.bmonoddsserver.config;

import com.rohanc.bmonoddsserver.auth.AuthSuccessHandler;
import com.rohanc.bmonoddsserver.services.UserCoreService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Supplier;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	private static final String CSRF_TOKEN_HEADERNAME = "X-XSRF-TOKEN";

	@Autowired
	private UserCoreService userCoreService;

	@Autowired
	private AuthSuccessHandler authSuccessHandler;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// TODO: define a const with /api and perhaps all urls
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
				.csrf(
						(csrf) -> csrf.ignoringRequestMatchers("/api/auth/**", "/api/match/**")
								.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
								.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				.authorizeHttpRequests(
						(authorize) -> authorize
								.requestMatchers("/api/user/**", "/api/bet/**")
								.authenticated()
								.requestMatchers("/api/**")
								.permitAll())
				.formLogin(
						(formLogin) -> formLogin
								.usernameParameter("username")
								.passwordParameter("password")
								.loginPage("/login")
								.failureUrl("/login?failed")
								.loginProcessingUrl("/api/auth/login")
								.successHandler(authSuccessHandler))
				.logout((logout) -> logout.logoutUrl("/api/auth/logout"));
		// .formLogin(Customizer.withDefaults()).rememberMe(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
		return authenticationManagerBuilder.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedOrigins(
				Arrays.asList("https://localhost/", "http://localhost/"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", CSRF_TOKEN_HEADERNAME));
		configuration.setExposedHeaders(Arrays.asList("Set-Cookie"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	private DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userCoreService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	final class SpaCsrfTokenRequestHandler extends CsrfTokenRequestAttributeHandler {
		private final CsrfTokenRequestHandler delegate = new XorCsrfTokenRequestAttributeHandler();

		@Override
		public void handle(
				HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> csrfToken) {
			this.delegate.handle(request, response, csrfToken);
		}

		@Override
		public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
			if (StringUtils.hasText(request.getHeader(csrfToken.getHeaderName()))) {
				return super.resolveCsrfTokenValue(request, csrfToken);
			}

			return this.delegate.resolveCsrfTokenValue(request, csrfToken);
		}
	}

	final class CsrfCookieFilter extends OncePerRequestFilter {

		@Override
		protected void doFilterInternal(
				HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
			csrfToken.getToken();

			filterChain.doFilter(request, response);
		}
	}
}
