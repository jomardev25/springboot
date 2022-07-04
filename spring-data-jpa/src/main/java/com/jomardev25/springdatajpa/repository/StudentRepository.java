package com.jomardev25.springdatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jomardev25.springdatajpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	/* Derived Queries */
	// The pattern is Queryname<java property><Operation>
	// https://dzone.com/articles/springdata-series-part-2-property-traversal
	// https://thorben-janssen.com/ultimate-guide-derived-queries-with-spring-data-jpa/
	// Property Traversal

	List<Student> findByIdIn(List<Integer> ids);

	List<Student> findStudentByCourses_Id(Integer id);

	List<Student> findByCoursesId(Integer id);

	Student findTop1ByCoursesIdAndUserIdOrderByCoursesTitleDesc(Integer courseId, Integer userId);

	Student findStudenByUser_FirstName(String firstName);

	Student findStudenByUserFirstNameIgnoreCaseAndUserLastNameIgnoreCase(String firstName, String lastName);

}
