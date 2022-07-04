package com.jomardev25.springdatajpa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jomardev25.springdatajpa.audit.SoftDelete;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString(exclude = "courseMaterial")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) //Convert camel case to snake case entire Entity property for json. Ex. firstName -> first_name
public class Course extends SoftDelete implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private int unit;

	@JsonManagedReference // to solved infinite loop due to relationship
	@OneToOne(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CourseMaterial courseMaterial;

	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // use this for fetch lazy loading
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Teacher teacher;

	@JsonIgnore
	@ManyToMany(mappedBy = "courses")
	private Set<Student> students;

}
