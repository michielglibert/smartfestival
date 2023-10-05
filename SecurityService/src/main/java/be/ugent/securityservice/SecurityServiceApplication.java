package be.ugent.securityservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.securityservice.adapters.messaging.Channels;
import be.ugent.securityservice.domain.Incident;
import be.ugent.securityservice.domain.Location;
import be.ugent.securityservice.domain.Severity;
import be.ugent.securityservice.persistence.IncidentRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner populateDatabase(IncidentRepository incidentRepository) {
		return (args) -> {
			Location location = new Location();
			List<String> hi = new ArrayList<String>();
			hi.add("lol");
			Incident incident = new Incident("lol", "hi", "iei", null, location, Severity.MINOR, hi);
			incidentRepository.save(incident);
		};
	}

}
