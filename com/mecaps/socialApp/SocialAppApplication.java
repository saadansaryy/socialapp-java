package com.mecaps.socialApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mecaps.socialApp")
public class SocialAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialAppApplication.class, args);
	}

}
