package com.adamtrev.portal;

import org.joda.time.DateTimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalApplication {
	public static void main(String[] args) {
		DateTimeZone.setDefault(DateTimeZone.UTC);
		SpringApplication.run(PortalApplication.class, args);
	}
}
