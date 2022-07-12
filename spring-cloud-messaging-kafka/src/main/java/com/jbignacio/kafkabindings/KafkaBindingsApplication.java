package com.jbignacio.kafkabindings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KafkaBindingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaBindingsApplication.class, args);
	}

}
