package com.jomardev25.springdatajpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jomardev25.springdatajpa.audit.Auditable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "menu_items")
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuItem extends Auditable<Integer> implements Serializable  {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToOne
	private Menu menu;

	@Column(length = 100)
	private String name;

	@Column(length = 50)
	private String label;

	private String components;

	private String meta;

	private String path;

	private String slug;

	@Column(length = 50)
	private String icon;

	@Column(length = 100)
	private String activeLink;

	@Column(length = 100)
	private String classes;

	@Column(length = 100)
	private String attribute;

	@BatchSize(size = 10)
	@OneToMany(fetch = FetchType.EAGER)
	@Column( name= "parent_id")
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private List<MenuItem> children;

	private int sort;

	private int sidebarMenuInd;

	@Column(name = "deleted_at", updatable = false, insertable = false, nullable = true)
	private Date deletedAt;

}
