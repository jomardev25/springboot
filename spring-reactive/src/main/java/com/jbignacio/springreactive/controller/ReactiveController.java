package com.jbignacio.springreactive.controller;

import java.time.Duration;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbignacio.springreactive.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {

	private static Flux<User> users;

	static {
		Date birthDate = new Date();
		users = Flux.just(new User(1L, "John", "Doe", birthDate), new User(2L, "Jane", "Doe", birthDate), new User(3L, "Anna Marie", "Doe", birthDate));
	}

	@GetMapping(value="/reactive")
	public Flux<Integer> flux(){
		return Flux.just(1, 2, 3, 4, 5).log();
	}

	@SuppressWarnings("deprecation")
	@GetMapping(value="/reactive-stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> fluxInteger(){
		return Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).log();
	}


	@SuppressWarnings("deprecation")
	@GetMapping(value="/reactive-stream-users", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<User> fluxUser(){
		return users.delayElements(Duration.ofSeconds(2)).log();
	}

	@SuppressWarnings("deprecation")
	@GetMapping(value="/reactive-stream-infinite", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Long> fluxIntegerInfinite(){
		return Flux.interval(Duration.ofSeconds(1)).log();
	}

	@GetMapping(value = "/mono", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Integer> monoOfLong(){
		return Mono.just(1).log();
	}

}
