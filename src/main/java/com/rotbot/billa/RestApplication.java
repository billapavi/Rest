package com.rotbot.billa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.rotbot.billa")
@SpringBootApplication(scanBasePackages="com.rotbot.billa")
@EntityScan(basePackages="com.rotbot.billa.stocks")
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
		
	}

}

