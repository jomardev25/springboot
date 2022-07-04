package com.jomardev25.softdelete.http.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.softdelete.entity.Teacher;
import com.jomardev25.softdelete.repository.TeacherRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/teachers")
@AllArgsConstructor
public class TeacherController {

	private TeacherRepository teacherRepository;

	@GetMapping
	private ResponseEntity<List<Teacher>> getAllTeachers(){
		return ResponseEntity.ok().body(teacherRepository.findAll());
	}

}
