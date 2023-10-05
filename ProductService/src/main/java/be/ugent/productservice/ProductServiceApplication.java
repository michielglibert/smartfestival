package be.ugent.productservice;

import be.ugent.productservice.adapters.messaging.Channels;
import be.ugent.productservice.domain.FestivalStand;
import be.ugent.productservice.domain.Order;
import be.ugent.productservice.domain.Product;
import be.ugent.productservice.persistence.OrderRepository;
import be.ugent.productservice.persistence.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import sun.tools.jar.CommandLine;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableBinding(Channels.class)
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateDatabase(ProductRepository productRepository, OrderRepository orderRepository){
		return (args) -> {
			FestivalStand fs1 = new FestivalStand("1", "Joe's burgers");
			FestivalStand fs2 = new FestivalStand("2", "Chuck's place");
			Product p1 = new Product("1", "Jupiler 25cl", 1.80);
			Product p2 = new Product("2", "Hamburger", 4.00);
			Product p3 = new Product("3", "Cara pils 33cl", 1.50);
			Product p4 = new Product("4", "Hotdog", 3.00);
			List<Product> products1 = new ArrayList<Product>();
			products1.add(p1);
			products1.add(p2);
			Order o1 = new Order("1", "1", products1, fs1);
			List<Product> products2 = new ArrayList<Product>();
			products2.add(p3);
			products2.add(p4);
			Order o2 = new Order("2", "2", products2, fs2);
			productRepository.saveAll(products1);
			productRepository.saveAll(products2);
			orderRepository.save(o1);
			orderRepository.save(o2);
		};
	}
}
