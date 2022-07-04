package com.jomardev25.springdatajpa.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jomardev25.springdatajpa.entity.MenuItem;
import com.jomardev25.springdatajpa.repository.MenuItemRepository;

@RestController
@RequestMapping("/api/v1/menu-items")
public class MenuItemController {

	@Autowired
	private MenuItemRepository menuItemRepository;

	@PersistenceContext
	EntityManager em;

	@GetMapping
	public ResponseEntity<List<MenuItem>> index(){
		List<MenuItem> menuItems = (List<MenuItem>) em.createQuery("select distinct m from MenuItem m left join fetch m.children", MenuItem.class).getResultList();
		return ResponseEntity.ok().body(menuItems);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Optional<MenuItem>> show(@PathVariable("id") int id){
		//return menuItemRepository.findById(id).orElse(ResponseEntity.notFound().build());
		Optional<MenuItem> menuItems = menuItemRepository.findById(id);
		return ResponseEntity.ok().body(menuItems);


	}


}
