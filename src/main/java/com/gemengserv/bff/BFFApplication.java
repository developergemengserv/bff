package com.gemengserv.bff;

import com.gemengserv.bff.config.SwaggerAggregator;
import com.gemengserv.bff.config.SwaggerRoutes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.gemengserv.bff")
@EnableConfigurationProperties(SwaggerRoutes.class)
public class BFFApplication {

	public static void main(String[] args) {
		SpringApplication.run(BFFApplication.class, args);
	}

}
