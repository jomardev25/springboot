package com.jomardev25.springdatajpa.serializer;

import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.format.DateTimeParseException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CourseDeSerializer extends StdDeserializer<Date>{

	private static final long serialVersionUID = 1L;

	protected CourseDeSerializer() {
		super(Date.class);
	}

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		System.out.println(p);
		System.out.println(ctxt);
		//String value = p.readValueAs(String.class);
//        try {
//            return new SimpleDateFormat("MM/dd/YYYY").parse(value);
//        } catch (DateTimeParseException e) {
//        	e.printStackTrace();
//        } catch (ParseException e) {
//			e.printStackTrace();
//		}
		return null;
	}
}
