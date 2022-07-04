package com.jomardev25.springdatajpa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jomardev25.springdatajpa.audit.SoftDelete;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "teachers")
@Entity
@Getter
@Setter
@ToString
@SQLDelete(sql = "update teachers set deleted_at=now() where id=?")
@Where(clause = "deleted_at is null")
public class Teacher extends SoftDelete implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonProperty("first_name") // Convert camel case to snake case individual property for json.
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // use this for fetch lazy loading
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Course.class)
	@JoinColumn(name="teacher_id")
	private Set<Course> courses;

}
