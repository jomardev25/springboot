package com.jbignacio.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.jbignacio.kafka.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class KafkaConfig {

	//@Value("${spring.kafka.consumer.bootstrap-servers}")
	//private String broker;

//	@Bean
//	public ProducerFactory<String, User> producerFactory() {
//		Map<String, Object> config = new HashMap<>();
//		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//		return new DefaultKafkaProducerFactory<>(config);
//	}
//
//	@Bean
//	public KafkaTemplate<String, User> kafkaTemplate() {
//		return new KafkaTemplate<>(producerFactory());
//	}
//
	@Bean
	public RecordMessageConverter converter() { // Convert String message to object
		return new JsonMessageConverter();
	}

//	@Bean
//    public NewTopic topic() {
//        return TopicBuilder.name("user-topic")
//                .partitions(1)
//                .replicas(3)
//                .compact()
//                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
//                .build();
//    }
//
//
//	@SuppressWarnings("deprecation")
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer, ConsumerFactory<Object, Object> kafkaConsumerFactory){
//		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//
//		/**
//         * By enabling this Concurrency Consumer Container will run in a 3 different threads.
//         * Each Thread will point to a partition.
//         */
//        factory.setConcurrency(3);
//        configurer.configure(factory, kafkaConsumerFactory);
//
//        /**
//         * Adding Custom Error handling mechanism
//         * By default Kafka Consumer Error handling mechanism would work. However, if you want to
//         * override these functionality and execute certain logic after error got thrown then below
//         * implementation would work.
//         */
//        factory.setErrorHandler(((thrownException, data) -> {
//            log.info("Exception in consumerConfig is {} and the record is {}", thrownException.getMessage(), data);
//            //persist
//
//            // DO SOME BUSINESS LOGIC AFTER ERROR GOT THROWN
//        }));
//
//        /**
//         * Retry Mechanism
//         */
//        factory.setRetryTemplate(retryTemplate());
//
//        /**
//         * Recovery Logic: Type - 1
//         */
//        factory.setRecoveryCallback((context -> {
//
//        	/**
//             * Here the cause of the exception is related to Data Access Exception
//             */
//
//        	if(context.getLastThrowable().getCause() instanceof RecoverableDataAccessException){
//                //invoke recovery logic
//                log.info("Inside the recoverable logic");
//
//
//            } else{
//                log.info("Inside the non recoverable logic");
//                throw new RuntimeException(context.getLastThrowable().getMessage());
//            }
//
//        	return null;
//
//        }));
//
//		return factory;
//
//	}
//
//	private RetryTemplate retryTemplate() {
//        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
//        fixedBackOffPolicy.setBackOffPeriod(1000); //Back Off time is 1 second, before applying retry
//
//        RetryTemplate retryTemplate = new RetryTemplate();
//        retryTemplate.setRetryPolicy(simpleRetryPolicy()); //step1
//        retryTemplate.setBackOffPolicy(fixedBackOffPolicy); //step2
//        return  retryTemplate;
//    }
//
//	private RetryPolicy simpleRetryPolicy() {
//
//        /**
//         * Scenario 1: With the below given configuration, any exception occurs during the
//         * execution logic, retry mechanisms would start.
//         *
//         * However, if my data itself is wrong and because of that application would throw
//         * an exception then it is not a proper retry.
//         *
//         * When to execute retry? If you got any specific exception or error then only you have
//         * to call the retry. Refer Scenario2
//         */
//        //SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
//        //simpleRetryPolicy.setMaxAttempts(3); //Retry 3 times
//
//        /**
//         * Scenario 2 Impl (Custom Retry logic)
//         */
//        Map<Class<? extends Throwable>, Boolean> exceptionsMap = new HashMap<>();
//        exceptionsMap.put(IllegalArgumentException.class, false);
//        exceptionsMap.put(RecoverableDataAccessException.class, true);
//
//        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(3, exceptionsMap,true);
//        return simpleRetryPolicy;
//    }

	@Bean
    public ConsumerFactory<String, User> consumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "console-consumer-70309");
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configs.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, 50);
        configs.put(ConsumerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, 5000);
        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new JsonDeserializer<>(User.class));
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, User>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
