package org.bp.adventureclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.bp.adventureclient.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Locale;

@SpringBootApplication
public class AdventureBookingClientApplication {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			AdventureBookingRequest request = new AdventureBookingRequest();
			
			// Person
			Person client = new Person();
			client.setFirstName("Jan");
			client.setLastName("Kowalski");
			request.setPerson(client);
			
			// Payment Card
			PaymentCard card = new PaymentCard();
			card.setName("Visa");
			card.setValidTo("04/21");
			card.setNumber("1234 5678 9101 1121");
			request.setPaymentCard(card);
			
			// Car
			Car car = new Car();
			car.setBrand("Volvo");
			car.setModel("S60");
			car.setRegistrationPlate("PZ92864");
			car.setFrom(formatter.parse("2021-02-23"));
			car.setTo(formatter.parse("2021-02-28"));
			request.setCar(car);
			
			// National Park
			EntranceFee fee = new EntranceFee();
			fee.setPark("Yellowstone");
			fee.setFrom(formatter.parse("2021-02-23"));
			fee.setTo(formatter.parse("2021-02-28"));
			request.setEntranceFee(fee);
			
			try {
				ResponseEntity<PaymentResponse> response = restTemplate.postForEntity("http://localhost:8085/api/adventure/booking",
						request, PaymentResponse.class);
				log.info("successful");

			} catch (HttpClientErrorException e) {
				log.info("error occurred");
				log.error(e.getResponseBodyAsString());
			}
		};
	}
			
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	private static final Logger log = LoggerFactory.getLogger(AdventureBookingClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AdventureBookingClientApplication.class, args);
	}

}