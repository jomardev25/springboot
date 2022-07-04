package com.jomardev25.springdatajpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserDTO {

	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private boolean isActive;

}
