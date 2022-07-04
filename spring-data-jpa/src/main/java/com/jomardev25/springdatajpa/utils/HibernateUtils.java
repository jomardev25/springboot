package com.jomardev25.springdatajpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

public class HibernateUtils {

	@PersistenceContext
	private static EntityManager entityManager;

	public static void toSql(CriteriaQuery<?> criteriaQuery) {
		System.out.println(entityManager.createQuery(criteriaQuery).unwrap(org.hibernate.query.Query.class).getQueryString());
	}

}
