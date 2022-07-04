package com.jomardev25.springdatajpa.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

	@CreatedBy
	@Column(nullable = true, updatable = false)
	protected T createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(nullable = true, updatable = false)
	protected Date createdDate;

	@LastModifiedBy
    protected T lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable = true, updatable = false)
    protected Date lastModifiedDate;

}
