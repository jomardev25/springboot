package com.jomardev25.softdelete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jomardev25.softdelete.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
