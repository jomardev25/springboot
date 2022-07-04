package com.jomardev25.springdatajpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.springdatajpa.dto.StudentDTO;
import com.jomardev25.springdatajpa.entity.Student;
import com.jomardev25.springdatajpa.response.StudentResponse;
import com.jomardev25.springdatajpa.service.StudentService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/students")
@RestController
@AllArgsConstructor
public class StudentController {

	private StudentService studentService;

	@GetMapping
	public List<StudentResponse> index(){
		return studentService.findAllStudents();
	}

	@GetMapping("/{ids}")
	public List<StudentResponse> getStudentByIds(@PathVariable(name = "ids") String ids){
		return studentService.findStudentByIds(ids);
	}

	@GetMapping("/by-courses-id/{id}")
	public List<Student> getStudentByCourseId(@PathVariable(name = "id") Integer id){
		return studentService.findStudentByCoursesId(id);
	}

	@GetMapping("/by-user-firstname/{firstName}")
	public Student getStudentByUserFirstName(@PathVariable(name = "firstName") String firstName){
		return studentService.findStudentByUserFirstName(firstName);
	}

	@GetMapping("/by-user/{firstName}/{lastName}")
	public Student getStudentByUserFirstNameAndLastName(@PathVariable(name = "firstName") String firstName, @PathVariable(name = "lastName") String lastName){
		return studentService.findStudentByUserFirstNameAndLastName(firstName, lastName);
	}

	@GetMapping("/by-courseid-and-userid/{courseId}/{userId}")
	public Student getStudentByCourseIdAndUserId(@PathVariable(name = "courseId") Integer courseId, @PathVariable(name = "userId") Integer userId){
		return studentService.findStudentByCourseIdAndUserId(courseId, userId);
	}

	@GetMapping("/criteria-builder/{studentId}/{userId}")
	public List<Student> getStudentByIdAndCourseId(@PathVariable(name = "studentId") Integer studentId, @PathVariable(name = "userId") Integer userId){
		return studentService.findStudentByIdAndUserId(studentId, userId);
	}

	@PostMapping
	public StudentResponse store(@RequestBody StudentDTO studentDTO) {
		return studentService.saveStudent(studentDTO);
	}

	@PutMapping("/{id}")
	public StudentResponse update(@PathVariable(name = "id") Integer id, @RequestBody StudentDTO studentDTO) {
		return studentService.updateStudent(id, studentDTO);
	}

}
