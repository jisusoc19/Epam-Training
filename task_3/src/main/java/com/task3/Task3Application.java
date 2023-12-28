package com.task3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Task3Application {
	private static final Logger logger = LoggerFactory.getLogger(Task3Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Task3Application.class, args);
		
	}
    @PostConstruct
    public void init() {
    	logger.info("main ha sido inicializado");
    }
    
    
    

}
