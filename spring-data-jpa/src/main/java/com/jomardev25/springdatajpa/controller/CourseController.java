package com.jomardev25.springdatajpa.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.springdatajpa.entity.Course;
import com.jomardev25.springdatajpa.entity.CourseMaterial;
import com.jomardev25.springdatajpa.entity.Teacher;
import com.jomardev25.springdatajpa.exception.ResourceNotFound;
import com.jomardev25.springdatajpa.filtering.SearchDTO;
import com.jomardev25.springdatajpa.filtering.SearchSpecification;
import com.jomardev25.springdatajpa.repository.CourseMaterialRepository;
import com.jomardev25.springdatajpa.repository.CourseRepository;
import com.jomardev25.springdatajpa.repository.TeacherRepository;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/courses")
@RestController
@AllArgsConstructor
public class CourseController {

	private CourseRepository courseRepository;
	private CourseMaterialRepository courseMaterialRepository;
	private TeacherRepository teacherRepository;

	@PostMapping("/search")
	public Page<Course> searchCourses(@RequestBody SearchDTO request){
		SearchSpecification<Course> specification = new SearchSpecification<>(request);
		Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
		return courseRepository.findAll(specification, pageable);
	}

	@GetMapping("/create-materials")
	public ResponseEntity<CourseMaterial> createCourseMaterial(){
		Course course = new Course();
		course.setTitle("C#");
		course.setUnit(3);

		CourseMaterial courseMaterial = new CourseMaterial();
		courseMaterial.setName("C# Programming Course");
		courseMaterial.setCourse(course);
		courseMaterial.setDescription("Programming");

		return new ResponseEntity<>(courseMaterialRepository.save(courseMaterial), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public Course getCourseById(@PathVariable("id") Integer id){

		return courseRepository.findById(7).orElseThrow(() -> new  ResourceNotFound("Course", "id", 6));

	}

	@GetMapping
	public Iterable<Course> getAllCourses(){
		//Integer[] ids= {6, 7, 8};

		Iterable<Course> courses = courseRepository.findAll();

		return courses;

	}

	@GetMapping("/get-course-materials")
	public CourseMaterial getCourseMaterial(){
		CourseMaterial courseMaterial = courseMaterialRepository.findById(10).orElseThrow(() -> new  ResourceNotFound("Course material", "id", 6));
		return courseMaterial;
	}

	@GetMapping("/get-teacher-courses")
	public Page<Teacher> insertTeacher(){

//		Course course1 = new Course();
//		course1.setTitle("Spring boot Starter");
//		course1.setUnit(3);
//
//		course1 = courseRepository.save(course1);
//
//		CourseMaterial courseMaterial1 = new CourseMaterial();
//		courseMaterial1.setName("Spring Boot Starter Course");
//		courseMaterial1.setCourse(course1);
//		courseMaterial1.setDescription("Programming Java");
//
//		courseMaterialRepository.save(courseMaterial1);
//
//		Course course2 = new Course();
//		course2.setTitle("Spring Actuator");
//		course2.setUnit(3);
//
//		course2 = courseRepository.save(course2);
//
//		CourseMaterial courseMaterial2 = new CourseMaterial();
//		courseMaterial2.setName("Spring Actuator Course");
//		courseMaterial2.setCourse(course2);
//		courseMaterial2.setDescription("Programming Java");
//
//		courseMaterialRepository.save(courseMaterial2);
//
//
//		Set<Course> courses = new HashSet<>();
//		courses.add(course1);
//		courses.add(course2);
//
//		Teacher teacher = new Teacher();
//		teacher.setFirstName("Mark");
//		teacher.setLastName("Santos");
//		teacher.setCourses(courses);

//		teacherRepository.save(teacher);

		Pageable pageable = PageRequest.of(0, 2, Sort.by("lastName").ascending());
		Page<Teacher> teachers = teacherRepository.findAll(pageable);
		return teachers;
	}

}
