package be.ugent.paymentadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import be.ugent.paymentadapter.adapter.Channels;

@SpringBootApplication
@EnableBinding(Channels.class)
public class PaymentAdapterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentAdapterServiceApplication.class, args);
	}

}
