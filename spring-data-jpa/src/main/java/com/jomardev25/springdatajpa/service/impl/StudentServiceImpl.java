package com.jomardev25.springdatajpa.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.jomardev25.springdatajpa.dto.StudentDTO;
import com.jomardev25.springdatajpa.entity.Course;
import com.jomardev25.springdatajpa.entity.Guardian;
import com.jomardev25.springdatajpa.entity.Student;
import com.jomardev25.springdatajpa.entity.Student_;
import com.jomardev25.springdatajpa.entity.User;
import com.jomardev25.springdatajpa.entity.User_;
import com.jomardev25.springdatajpa.exception.ResourceNotFound;
import com.jomardev25.springdatajpa.repository.StudentRepository;
import com.jomardev25.springdatajpa.repository.UserRepository;
import com.jomardev25.springdatajpa.response.StudentResponse;
import com.jomardev25.springdatajpa.service.StudentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

	private static Logger logger = LogManager.getLogger();

	private StudentRepository studentRepo;
	private UserRepository userRepo;
	private EntityManager em;

	public List<StudentResponse> findAllStudents() {
		return studentRepo.findAll(Sort.by(Sort.Direction.ASC, "id")).stream().map(student -> mapToResponse(student)).collect(Collectors.toList());
	}

	public StudentResponse findStudentById(int id) {
		Student student = studentRepo.findById(id).orElseThrow(() -> new  ResourceNotFound("Student", "id", id));
		return mapToResponse(student);
	}

	public List<StudentResponse> findStudentByIds(String ids){
		List<Integer> listOfIds =  Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		return studentRepo.findByIdIn(listOfIds).stream().map(student -> mapToResponse(student)).collect(Collectors.toList());
	}

	public StudentResponse saveStudent(StudentDTO studentDTO) {
		return mapToResponse(studentRepo.save(mapToEntity(studentDTO)));
	}

	public StudentResponse updateStudent(Integer id, StudentDTO studentDTO) {
		Student student = studentRepo.findById(id).orElseThrow(() -> new  ResourceNotFound("Student", "id", id));
		Guardian guardian = new Guardian();
		guardian.setName(studentDTO.getGuardianName());
		guardian.setAddress(studentDTO.getGuardianAddress());
		guardian.setPhoneNum(studentDTO.getGuardianPhoneNum());

		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setActive(studentDTO.isActive());
		student.setGuardian(guardian);

		return mapToResponse(studentRepo.save(student));
	}

	private Student mapToEntity(StudentDTO studentDTO) {

		User user = userRepo.findById(studentDTO.getUserId()).orElseThrow(() -> new ResourceNotFound("User", "id", studentDTO.getUserId()));
		Guardian guardian = new Guardian();
		guardian.setName(studentDTO.getGuardianName());
		guardian.setAddress(studentDTO.getGuardianAddress());
		guardian.setPhoneNum(studentDTO.getGuardianPhoneNum());


		return Student.builder()
				.id(studentDTO.getId())
				.firstName(studentDTO.getFirstName())
				.lastName(studentDTO.getLastName())
				.isActive(studentDTO.isActive())
				.guardian(guardian)
				.user(user)
				.build();

	}

	private StudentResponse mapToResponse(Student student) {
		Guardian guardian = new Guardian();
		guardian.setName(student.getGuardian().getName());
		guardian.setAddress(student.getGuardian().getAddress());
		guardian.setPhoneNum(student.getGuardian().getPhoneNum());


		return StudentResponse.builder()
				.id(student.getId())
				.firstName(student.getFirstName())
				.lastName(student.getLastName())
				.isActive(student.isActive())
				.guardian(guardian)
				.courses(student.getCourses())
				.user(student.getUser())
				.build();

	}

	@Override
	public List<Student> findStudentByCoursesId(Integer id) {
		logger.info(id);
		// studentRepo.findStudentByCourses_Id(id); Property Traversal
		return studentRepo.findByCoursesId(id);
	}

	@Override
	public Student findStudentByUserFirstName(String firstName) {
		return studentRepo.findStudenByUser_FirstName(firstName);
	}

	@Override
	public Student findStudentByUserFirstNameAndLastName(String firstName, String lastName) {
		logger.info(firstName + " " + lastName);
		return studentRepo.findStudenByUserFirstNameIgnoreCaseAndUserLastNameIgnoreCase(firstName, lastName);
	}

	@Override
	public Student findStudentByCourseIdAndUserId(Integer courseId, Integer userId) {
		return studentRepo.findTop1ByCoursesIdAndUserIdOrderByCoursesTitleDesc(courseId, userId);
	}

	@Override
	public List<Student> findStudentByIdAndUserId(Integer studentId, Integer userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Student> cq = cb.createQuery(Student.class);
		Root<Student> student = cq.from(Student.class);
		Join<Student, Course> user = student.join(Student_.USER, JoinType.INNER);

		ParameterExpression<Integer> pStudentId = cb.parameter(Integer.class);
		ParameterExpression<Integer> pUserId = cb.parameter(Integer.class);

		cq.where(cb.equal(student.get(Student_.ID), pStudentId), cb.equal(user.get(User_.ID), pUserId));


		TypedQuery<Student> query = em.createQuery(cq);
		//HibernateUtils.toSql(cq);

		query.setParameter(pStudentId, studentId);
		query.setParameter(pUserId, userId);

		return query.getResultList();
	}

}
