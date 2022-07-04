package com.jomardev25.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jomardev25.springdatajpa.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

}
