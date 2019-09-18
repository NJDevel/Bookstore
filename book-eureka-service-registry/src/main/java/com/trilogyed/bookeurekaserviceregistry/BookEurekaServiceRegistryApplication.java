package com.trilogyed.bookeurekaserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookEurekaServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookEurekaServiceRegistryApplication.class, args);
	}

}
