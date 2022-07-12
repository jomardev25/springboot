package com.jbignacio.kafkabindings;

import java.io.IOException;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadDeSerializer implements Deserializer<Payload> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Payload deserialize(String topic, byte[] data) {
		try {
			System.out.println("Deeserializing payload...");
			return objectMapper.readValue(new String(data), Payload.class);
		} catch (IOException e) {
			System.out.println("Error in deserializing payload");
			throw new SerializationException(e);
		}
	}

}
