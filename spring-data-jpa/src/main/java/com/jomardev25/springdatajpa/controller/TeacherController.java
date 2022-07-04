package com.jomardev25.springdatajpa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.springdatajpa.entity.Teacher;
import com.jomardev25.springdatajpa.repository.TeacherRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/teachers")
@AllArgsConstructor
public class TeacherController{

	private TeacherRepository teacherRepository;

	@GetMapping
	public ResponseEntity<List<Teacher>> getAllTeachers(){
		return ResponseEntity.ok().body(teacherRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable(name = "id") Integer id){
		Teacher teacher = teacherRepository.findById(id).get();
		return ResponseEntity.ok().body(teacher);
	}

	@GetMapping("/with-courses-count")
	public ResponseEntity<Object> getTeacherByIdWithCoursesCount(){
		return ResponseEntity.ok().body(teacherRepository.findCourseCount());
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<Teacher> deleteTeacherById(@PathVariable(name = "id") Integer id){
		Teacher teacher = teacherRepository.findById(id).get();
		teacherRepository.deleteById(id);
		return ResponseEntity.ok().body(teacher);
	}

}
