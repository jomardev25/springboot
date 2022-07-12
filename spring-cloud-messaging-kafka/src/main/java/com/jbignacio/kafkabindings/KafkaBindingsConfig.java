package com.jbignacio.kafkabindings;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaBindingsConfig {

	@Bean
	public Function<Payload, Payload> processor(){
		return ouput -> ouput;
	}

	@Bean
	public Consumer<Payload> consummer(){
		return event -> System.out.println(event);
	}

}
