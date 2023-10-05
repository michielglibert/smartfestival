package be.ugent.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}	

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Notification service routes
				.route(r -> r.host("*").and().path("/notification/**").uri("http://notification:2223"))
				//.route(r -> r.host("*").and().path("/notification/**").uri("http://localhost:2223"))
				// Security service routes
				.route(r -> r.host("*").and().path("/security/**").uri("http://security:2224"))
				//.route(r -> r.host("*").and().path("/security/**").uri("http://localhost:2224"))
				// Ticket service routes
				.route(r -> r.host("*").and().path("/ticket/**").uri("http://ticket:2225"))
				//.route(r -> r.host("*").and().path("/ticket/**").uri("http://localhost:2225"))
				// Visitor service routes
				.route(r -> r.host("*").and().path("/visitor/**").uri("http://visitor:2226"))
				//.route(r -> r.host("*").and().path("/visitor/**").uri("http://localhost:2226"))
				// Product Service routes
				.route(r -> r.host("*").and().path("/product/**").uri("http://product:2227"))
				//.route(r -> r.host("*").and().path("/product/**").uri("http://localhost:2227"))
				// Balance Service routes
				.route(r -> r.host("*").and().path("/balance/**").uri("http://balance:2228"))
				//.route(r -> r.host("*").and().path("/balance/**").uri("http://localhost:2228"))
				// LineUp service routes
				.route(r -> r.host("*").and().path("/lineup/**").uri("http://lineup:2229"))
				//.route(r -> r.host("*").and().path("/lineup/**").uri("http://localhost:2229"))
				// Display service routes
				.route(r -> r.host("*").and().path("/display/**").uri("http://display:2230"))
				//.route(r -> r.host("*").and().path("/display/**").uri("http://localhost:2230"))
				.build();
	}

}
