package com.jomardev25.springdatajpa.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jomardev25.springdatajpa.entity.Course;
import com.jomardev25.springdatajpa.entity.Course_;
import com.jomardev25.springdatajpa.entity.Teacher;
import com.jomardev25.springdatajpa.entity.Teacher_;

public class TeacherRepositoryImpl {

	private static Logger logger = LogManager.getLogger();

	@PersistenceContext
	private EntityManager entityManager;

	public List<Object> findCourseCount(){
		@SuppressWarnings("unused")
		List<Object> objects = new ArrayList<>();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> teacherQuery = cb.createQuery(Object.class);
		@SuppressWarnings("unused")
		Root<Teacher> teacher = teacherQuery.from(Teacher.class);

		// create subquery
		Subquery<Long> subQuery = teacherQuery.subquery(Long.class);

		// subquery from
		Root<Course> course = subQuery.from(Course.class);

		//subquery selection
		subQuery.select(cb.count(course.get(Course_.ID)));
		//teacherQuery.select(teacher);

		teacherQuery.multiselect( course);

		//Join<Course, Teacher> course = teacher.join(Teacher_.COURSES, JoinType.LEFT);
		//Subquery<Object> subqQuery = cq.subquery(null);
		//((CriteriaQuery<Object>) course).select(cb.count(course.get(Course_.ID)));


		TypedQuery<Object> query = entityManager.createQuery(teacherQuery);



		logger.info(query.unwrap(org.hibernate.query.Query.class).getQueryString());

		return  query.getResultList();
	}
}
