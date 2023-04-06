package uk.ac.leedsbeckett.Library.Portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //provides component scanning, autoconfiguration and property support
public class LibraryPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryPortalApplication.class, args);
	}

}
