package com.jomardev25.springdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.jomardev25.springdatajpa.audit.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

	@Bean
	public AuditorAware<Integer> auditorAware(){
		return new AuditorAwareImpl();
	}

}
