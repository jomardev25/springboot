package com.jomardev25.softdelete.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Table(name = "teachers")
@Entity
@Data
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonProperty("first_name") // Convert camel case to snake case individual property for json.
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // use this for fetch lazy loading
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="teacher_id", referencedColumnName = "id")
	private Set<Course> courses;

}
