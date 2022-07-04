package com.jomardev25.springdatajpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.springdatajpa.dto.UserDTO;
import com.jomardev25.springdatajpa.entity.User;
import com.jomardev25.springdatajpa.repository.interfaces.IUsersWithStudents;
import com.jomardev25.springdatajpa.response.UserResponse;
import com.jomardev25.springdatajpa.response.UserWithStudentsResponse;
import com.jomardev25.springdatajpa.service.UserService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/users")
@RestController
@AllArgsConstructor
public class UserController {

	private UserService userService;

	@GetMapping
	public List<UserResponse> index(){
		return userService.findAllUsers();
	}

	@PostMapping
	public UserResponse store(@RequestBody UserDTO userDTO) {
		return userService.saveUser(userDTO);
	}

	@PutMapping("/{id}")
	public UserResponse store(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
		return userService.updateUser(id, userDTO);
	}

	@GetMapping("/user-with-students/{id}")
	public UserWithStudentsResponse getUserWithStudents(@PathVariable("id") Integer id) {
		return userService.findUserWithStudents(id);
	}

	@GetMapping("/users-with-students")
	public List<IUsersWithStudents> getUsersWithStudents() {
		return userService.findUsersWithStudents(2, "john");
	}

	@GetMapping("/users-with-students-query")
	public List<User> getUsersWithStudentsQuery() {
		return userService.findUserWithStudentQuery();
	}



}
