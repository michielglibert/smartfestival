package be.ugent.displayservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.displayservice.adapters.messaging.Channels;
import be.ugent.displayservice.persistence.MessageService;

@SpringBootApplication
@EnableBinding(Channels.class)
public class DisplayServiceApplication {
	private static Logger logger = LoggerFactory.getLogger(DisplayServiceApplication.class);
	
	@Bean
	public CommandLineRunner test(MessageService messageService) {
		return (args) -> {
			logger.info("database opvullen...");
			
			/***
			messageService.addMessage(new DisplayMessage("Happy hour", "Vandaag van 20 tot 21 uur pintjes aan 1 euro!", 1));
			messageService.addMessage(new DisplayMessage("Hotdogstand gesloten", "De hotdogstand is volledig uitverkocht!", 1));
			messageService.addMessage(new DisplayMessage("Brand", "Brand op stage B!", 5));
			***/
			logger.info("database opgevuld...");
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DisplayServiceApplication.class, args);
	}

}
