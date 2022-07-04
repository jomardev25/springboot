package com.jomardev25.springdatajpa.response;

import java.util.Set;

import com.jomardev25.springdatajpa.entity.Course;
import com.jomardev25.springdatajpa.entity.Guardian;
import com.jomardev25.springdatajpa.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class StudentResponse {

	private int id;
	private String firstName;
	private String lastName;
	private Guardian guardian;
	private User user;
	private Set<Course> courses;
	private boolean isActive;

}
