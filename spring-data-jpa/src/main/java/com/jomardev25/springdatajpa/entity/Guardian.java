package com.jomardev25.springdatajpa.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@AttributeOverrides({
	@AttributeOverride(
			name = "name",
			column = @Column(name="guardian_name")
	),
	@AttributeOverride(
			name = "address",
			column = @Column(name="guardian_address")
	),
	@AttributeOverride(
			name = "phoneNum",
			column = @Column(name="guardian_phone_num")
	)
})
public class Guardian implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phoneNum;

}
