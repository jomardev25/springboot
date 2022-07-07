package com.jbignacio.kafka.controller;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbignacio.kafka.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("v1/users")
@AllArgsConstructor
public class UserController {

	private final KafkaTemplate<String, User> kafkTemplate;
	private static final String TOPIC = "user-topic";

	@PostMapping
	public User store(@RequestBody User user) {
		String id = UUID.randomUUID().toString();
		log.info("event-id: {} {}", id, user.toString());
		kafkTemplate.send(TOPIC, id, user);
		return user;
	}

}
