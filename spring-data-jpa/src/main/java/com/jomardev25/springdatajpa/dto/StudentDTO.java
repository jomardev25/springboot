package com.jomardev25.springdatajpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class StudentDTO {

	private int id;
	private String firstName;
	private String lastName;
	private String guardianName;
	private String guardianAddress;
	private String guardianPhoneNum;
	private int userId;
	private boolean isActive;

}
