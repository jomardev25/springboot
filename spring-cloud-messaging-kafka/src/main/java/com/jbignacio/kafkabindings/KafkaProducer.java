package com.jbignacio.kafkabindings;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

	@Autowired
	private StreamBridge bridge;

	@Scheduled(cron = "*/2 * * * * *")
	public void send() {
		/*
		 * Payload message = new Payload("Message from StreamBridge " + new Date());
		 * Message<Payload> payload = MessageBuilder.withPayload(message)
		 * .setHeader(MESSAGE_KEY, message.getEventId()) .build();
		 */

		Payload message = new Payload("Message from StreamBridge " + new Date());
		System.out.println("Event Id: " + message.getEventId());
		bridge.send("processor-out-0", message);
	}

}
