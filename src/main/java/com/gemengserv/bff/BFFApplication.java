package com.gemengserv.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.gemengserv.bff")
public class BFFApplication {

	public static void main(String[] args) {
		SpringApplication.run(BFFApplication.class, args);
	}

}
