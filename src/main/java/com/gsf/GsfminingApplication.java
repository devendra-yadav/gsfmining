package com.gsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GsfminingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsfminingApplication.class, args);
	}

}
