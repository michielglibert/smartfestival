package be.ugent.notificationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.notificationservice.adapters.messaging.Channels;

@SpringBootApplication
@EnableBinding(Channels.class)
public class NotificationServiceApplication {
	private static Logger logger = LoggerFactory.getLogger(NotificationServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
		
	@Bean
	public CommandLineRunner populateDatabase() {
		return (args) -> {
			//SecurityNotification notification = new SecurityNotification(1L, "test", "dit is content", Priority.MEDIUM);
			logger.info("Starting");
		};
	}

}
