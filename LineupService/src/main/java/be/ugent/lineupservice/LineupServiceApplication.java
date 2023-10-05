package be.ugent.lineupservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.lineupservice.adapters.messaging.Channels;
import be.ugent.lineupservice.persistence.TimeslotRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class LineupServiceApplication {
	private static Logger logger = LoggerFactory.getLogger(LineupServiceApplication.class);
	
	@Bean
	public CommandLineRunner test(TimeslotRepository timeslotRepository) {
		return (args) -> {
			logger.info("database opvullen...");
			
			/***
			timeslotRepository.save(new Timeslot("Linkin Park", "B",
					LocalDateTime.of(2020, Month.JULY, 29, 19, 30, 00),
					LocalDateTime.of(2020, Month.JULY, 29, 20, 45, 00)));
			timeslotRepository.save(new Timeslot("The Kompressor Experiment", "B",
					LocalDateTime.of(2020, Month.JULY, 29, 21, 00, 00),
					LocalDateTime.of(2020, Month.JULY, 29, 22, 30, 00)));
					***/
			
			logger.info("database opgevuld...");
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LineupServiceApplication.class, args);
	}

}
