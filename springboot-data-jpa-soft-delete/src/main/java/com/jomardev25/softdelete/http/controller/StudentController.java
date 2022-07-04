package com.jomardev25.softdelete.http.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.softdelete.entity.Student;
import com.jomardev25.softdelete.repository.StudentRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

	StudentRepository studentRepository;

	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		return ResponseEntity.ok().body(studentRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
	}

}
