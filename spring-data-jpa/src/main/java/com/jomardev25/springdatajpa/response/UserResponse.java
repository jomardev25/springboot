package com.jomardev25.springdatajpa.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserResponse {

	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private boolean isActive;

}
