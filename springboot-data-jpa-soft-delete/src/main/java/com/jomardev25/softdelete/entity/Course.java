package com.jomardev25.softdelete.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString(exclude =  "courseMaterial")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@SQLDelete(sql = "update courses set deleted=now() where id=?")
@Where(clause = "deleted_at is null")
public class Course  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private int unit;

	@JsonManagedReference
	@OneToOne(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CourseMaterial courseMaterial;

	@JsonIgnore
	@ManyToMany(mappedBy = "courses")
	private Set<Student> students;

	@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Teacher teacher;

	@Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
