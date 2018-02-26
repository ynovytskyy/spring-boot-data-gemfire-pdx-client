package org.yny.sample.gemfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a sample application to demonstrate (otherwise rather convoluted) connection
 * to external GemFire from Spring Boot based Java application to GemFire as a client
 * (e.g. not as a peer) using Spring Data abstraction
 */
@SpringBootApplication
public class GemfireClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GemfireClientApplication.class, args);
	}

}
