package com.jomardev25.springdatajpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jomardev25.springdatajpa.audit.Auditable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "menus")
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu extends Auditable<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String menuDesc;

	


}
