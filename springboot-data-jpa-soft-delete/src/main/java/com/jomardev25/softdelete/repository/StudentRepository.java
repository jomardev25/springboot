package com.jomardev25.softdelete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jomardev25.softdelete.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
