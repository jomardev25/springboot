package com.jbignacio.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jbignacio.kafka.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventsConsumer {

//	@KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "console-consumer-70309")
//	public void on(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
//		 log.info("ConsumerRecord : {} ", consumerRecord.value());
//	}

//	@KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "console-consumer-70309")
//	public void on(ConsumerRecord<String, User> records) {
//		 log.info("ConsumerRecord : {} ", records);
//	}
//
	@KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "console-consumer-70309")
	public void on(User user) {
		 log.info("User : {} ", user.toString());
	}

}
