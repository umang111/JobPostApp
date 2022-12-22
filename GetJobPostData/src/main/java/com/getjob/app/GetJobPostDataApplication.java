package com.getjob.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GetJobPostDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetJobPostDataApplication.class, args);
	}

}
