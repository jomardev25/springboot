package com.jomardev25.springdatajpa.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class SoftDelete {

	@Column(name = "deleted_at", updatable = false, insertable = false, nullable = true)
	private Date deletedAt;

}
