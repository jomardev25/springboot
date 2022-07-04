package com.jomardev25.softdelete.http.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.softdelete.entity.Course;
import com.jomardev25.softdelete.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/courses")
@AllArgsConstructor
public class CourseController {

	private CourseRepository courseRepository;

	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses() {
		return ResponseEntity.ok().body(courseRepository.findAll());
	}

}
