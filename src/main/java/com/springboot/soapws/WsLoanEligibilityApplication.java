package com.springboot.soapws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
public class WsLoanEligibilityApplication extends SpringServletContainerInitializer{

	public static void main(String[] args) {
		SpringApplication.run(WsLoanEligibilityApplication.class, args);
	}

}
