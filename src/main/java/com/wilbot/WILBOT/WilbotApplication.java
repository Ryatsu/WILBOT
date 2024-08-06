package com.wilbot.WILBOT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wilbot.WILBOT")
public class WilbotApplication {
	public static void main(String[] args) {
		SpringApplication.run(WilbotApplication.class, args);
	}
}
