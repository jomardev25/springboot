package com.jomardev25.springdatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jomardev25.springdatajpa.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	List<Object> findCourseCount();

}
