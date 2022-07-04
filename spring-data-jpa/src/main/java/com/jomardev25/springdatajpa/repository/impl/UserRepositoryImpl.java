package com.jomardev25.springdatajpa.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import org.springframework.data.util.Pair;

import com.jomardev25.springdatajpa.entity.Student;

public class UserRepositoryImpl{

	@PersistenceContext
	EntityManager entityManager;

	public Pair<Long, List<Student>> findUserWithStudents(Integer id){

		Query totalCountQuery = entityManager.createQuery("select count(*) from User u where u.id=?1");
		totalCountQuery.setParameter(1, id);


		Long totalCount = (Long) totalCountQuery.getSingleResult();
		TypedQuery<Student> query =  entityManager.createQuery("select s from Student s where user.id=?1", Student.class);
		query.setParameter(1, id);

		return Pair.of(totalCount, query.getResultList());

	}

}
