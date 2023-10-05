package be.ugent.balanceservice;

import be.ugent.balanceservice.adapters.messaging.Channels;
import be.ugent.balanceservice.domain.Balance;
import be.ugent.balanceservice.persistence.BalanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(Channels.class)
public class BalanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalanceServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateDatabase(BalanceRepository balanceRepository){
		return (args) -> {
			/*Balance b1 = new Balance("1",0.0);
			Balance b2 = new Balance("2",10.0);
			balanceRepository.save(b1);
			balanceRepository.save(b2);*/
		};
	}
}
