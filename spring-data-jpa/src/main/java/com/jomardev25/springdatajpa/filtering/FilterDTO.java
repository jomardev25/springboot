package com.jomardev25.springdatajpa.filtering;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key;
	private Operator operator;
	private FieldType fieldType;
	private transient Object value;
    private transient Object valueTo;
    private transient List<Object> values;

}
