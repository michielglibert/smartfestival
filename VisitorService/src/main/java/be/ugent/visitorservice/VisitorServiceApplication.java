package be.ugent.visitorservice;

import be.ugent.visitorservice.adapters.messaging.Channels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(Channels.class)
public class VisitorServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(VisitorServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VisitorServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner populateDatabase() {
        return (args) -> {
            logger.info("Starting VisitorService");
        };
    }
}