package com.jomardev25.softdelete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jomardev25.softdelete.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByIdIn(Integer[] ids);

}
