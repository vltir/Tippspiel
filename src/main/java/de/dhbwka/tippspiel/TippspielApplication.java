package de.dhbwka.tippspiel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "de.dhbwka.tippspiel.entities")
public class TippspielApplication {

	public static void main(String[] args) {
		SpringApplication.run(TippspielApplication.class, args);
	}

}
