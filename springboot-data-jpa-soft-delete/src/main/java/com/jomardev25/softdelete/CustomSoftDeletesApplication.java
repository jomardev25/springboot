package com.jomardev25.softdelete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jomardev25.softdelete.config.CustomJpaRepositoryFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomJpaRepositoryFactoryBean.class)
public class CustomSoftDeletesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomSoftDeletesApplication.class, args);
	}

}
