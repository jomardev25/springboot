package com.jbignacio.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaApplication.class, args);

//		Properties properties = new Properties();
//		properties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
//		properties.put("kafka.topic"      , "user-topic");
//		properties.put("key.deserializer"   , "org.apache.kafka.common.serialization.StringDeserializer");
//		properties.put("value.deserializer" , "org.apache.kafka.common.serialization.StringDeserializer");
//		properties.put("group.id"          , "my-group");
//
//		KafkaConsumer consumer = new KafkaConsumer(properties);
//		consumer.subscribe(Arrays.asList(properties.getProperty("kafka.topic")));
//
//		System.out.println("Kafka Consumer in Java for topic (receives every 3 sec)" + properties.getProperty("kafka.topic"));
//
//  	    while(true) {
//  	    	ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//  	  	    for (ConsumerRecord record : records)  {
//  	  	    	System.out.printf("partition = %s, offset = %d, key = %s, value = %s\n",
//  	  	    			record.partition(), record.offset(), record.key(), record.value());
//  	  	    }
//  	    }
	}

}
