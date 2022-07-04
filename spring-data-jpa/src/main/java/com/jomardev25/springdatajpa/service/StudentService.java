package com.jomardev25.springdatajpa.service;

import java.util.List;

import com.jomardev25.springdatajpa.dto.StudentDTO;
import com.jomardev25.springdatajpa.entity.Student;
import com.jomardev25.springdatajpa.response.StudentResponse;

public interface StudentService {


	List<StudentResponse> findAllStudents();

	StudentResponse findStudentById(int id);

	StudentResponse saveStudent(StudentDTO studentDTO);

	StudentResponse updateStudent(Integer id, StudentDTO studentDTO);

	List<StudentResponse> findStudentByIds(String ids);

	List<Student> findStudentByCoursesId(Integer id);

	Student findStudentByCourseIdAndUserId(Integer courseId, Integer userId);

	Student findStudentByUserFirstName(String firstName);

	Student findStudentByUserFirstNameAndLastName(String firstName, String lastName);

	List<Student> findStudentByIdAndUserId(Integer studentId, Integer userId);

}
