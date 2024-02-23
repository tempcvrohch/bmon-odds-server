package com.rohanc.bmonoddsserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptConfig {

	@Bean
	public PasswordEncoder encoder() {
		return (PasswordEncoder) new BCryptPasswordEncoder();
	}
}
