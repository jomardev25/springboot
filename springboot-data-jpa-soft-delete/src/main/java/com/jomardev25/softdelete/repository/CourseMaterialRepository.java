package com.jomardev25.softdelete.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jomardev25.softdelete.entity.CourseMaterial;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Integer> {


}
