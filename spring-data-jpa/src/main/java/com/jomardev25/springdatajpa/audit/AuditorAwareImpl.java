package com.jomardev25.springdatajpa.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<Integer> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Optional getCurrentAuditor() {
		return Optional.of(3);
	}

}

