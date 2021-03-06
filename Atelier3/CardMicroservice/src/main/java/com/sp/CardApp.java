package com.sp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class CardApp {
	
    @Autowired
    private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(CardApp.class,args);
	}
	

	
}

