package com.wilbot.WILBOT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class WilbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(WilbotApplication.class, args);
	}

}
