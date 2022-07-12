package com.jbignacio.kafkabindings;

import java.io.Serializable;
import java.util.UUID;

public class Payload implements Serializable {

	private static final long serialVersionUID = 1L;
	private final UUID eventId = UUID.randomUUID();
	private String message;

	public Payload() {

	}

	public Payload(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UUID getEventId() {
		return eventId;
	}

	@Override
	public String toString() {
		return "Payload [eventId=" + eventId + ", message=" + message + "]";
	}

}
