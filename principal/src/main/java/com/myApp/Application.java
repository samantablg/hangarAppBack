package com.myApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan("com.myApp")
//@EnableAutoConfiguration

@SpringBootApplication
@EnableJpaRepositories("com.myApp")
@EntityScan("com.myApp")
public class Application{

	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}


}
