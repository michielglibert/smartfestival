package be.ugent.ticketservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.ticketservice.adapters.messaging.Channels;

@SpringBootApplication
@EnableBinding(Channels.class)
public class TicketServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(TicketServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner populateDatabase() {
		return (args) -> {
			logger.info("Starting TicketService");
		};
	}
}
