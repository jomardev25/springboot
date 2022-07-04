package com.jomardev25.springdatajpa.response;

import java.util.List;

import com.jomardev25.springdatajpa.entity.Student;

import lombok.Data;

@Data
public class UserWithStudentsResponse {

	private Long totalCount;
	private List<Student> students;

}
