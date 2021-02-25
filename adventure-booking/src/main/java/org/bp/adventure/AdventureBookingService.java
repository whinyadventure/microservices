package org.bp.adventure;

import static org.apache.camel.model.rest.RestParamType.body;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.model.rest.RestBindingMode;
import org.bp.adventure.model.AdventureBookingRequest;
import org.bp.adventure.model.Utils;
import org.bp.payment.PaymentRequest;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.stereotype.Component ;

@Component
public class AdventureBookingService extends RouteBuilder {
	
	@org.springframework.beans.factory.annotation.Autowired
	BookingIdentifierService bookingIdentifierService;

	@Override
	public void configure() throws Exception {
		
		restConfiguration()
			.component("servlet")
			.bindingMode(RestBindingMode.json)
			.dataFormatProperty("prettyPrint", "true")
			.enableCORS(true)
			.contextPath("/api")
			.apiContextPath("/api-doc")
			.apiProperty("api.title", "Adventure booking API")
			.apiProperty("api.version", "1.0.0");
		
		rest("/adventure").description("Adventure booking REST service")
			.consumes("application/json")
			.produces("application/json")
			.post("/booking").description("Book an adventure").type(AdventureBookingRequest.class).outType(org.bp.payment.PaymentResponse.class)
			.param().name("body").type(body).description("The adventure to book").endParam()
			.responseMessage().code(200).message("Adventure successfully booked").endResponseMessage()
			.to("direct:bookAdventure")
			.get("/booking/payment/{id}")
			.to("direct:bookingPayment");
		
		from("direct:bookAdventure").routeId("bookAdventure")
			.log("bookAdventure fired")
			.process((exchange) -> {
						exchange.setProperty("paymentRequest",
								Utils.preparePaymentRequest(exchange.getMessage().getBody(AdventureBookingRequest.class)));
						exchange.setProperty("adventureBookingId", bookingIdentifierService.generateBookingId());
					})
			.saga()
			.multicast()
			.parallelProcessing()
			.aggregationStrategy((prevEx, currentEx) -> {
					if (currentEx.getException() != null)
						return currentEx;
					if (prevEx != null && prevEx.getException() != null)
						return prevEx;
					
					org.bp.payment.PaymentRequest paymentRequest;
					if (prevEx == null)
						paymentRequest = currentEx.getProperty("paymentRequest", org.bp.payment.PaymentRequest.class);
					else
						paymentRequest = prevEx.getMessage().getBody(org.bp.payment.PaymentRequest.class);
					
					Object body = currentEx.getMessage().getBody();
					int cost;
					if (body instanceof org.bp.BookingInfo)
						cost = ((org.bp.BookingInfo)body).getCost();
					else if (body instanceof org.bp.park.BookParkEntranceResponse)
						cost = ((org.bp.park.BookParkEntranceResponse)body).getReturn().getCost();
					else
						return prevEx;
					
					paymentRequest.setAmount(paymentRequest.getAmount() + cost);
					currentEx.getMessage().setBody(paymentRequest);
					
					return currentEx;
					})
			.to("direct:bookParkEntrance")
			.to("direct:bookCar")
			.end()
			.process((currentEx) -> 
				{currentEx.getMessage().setBody(
						currentEx.getProperty("paymentRequest", org.bp.payment.PaymentRequest.class));
				})
			.to("direct:payment")
			.removeHeaders("Camel*")
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
		
		// serialize/deserialize model objects to/from XML
		final JaxbDataFormat jaxbParkEntrance = new JaxbDataFormat(org.bp.park.BookParkEntranceResponse.class.getPackage().getName());
		
		// end-point direct:bookParkEntrance
		from("direct:bookParkEntrance").routeId("bookParkEntrance")
			.log("bookParkEntrance fired")
			.saga()
				.propagation(SagaPropagation.MANDATORY)
				.compensation("direct:cancelParkEntrance").option("adventureBookingId", simple("${exchangeProperty.adventureBookingId}"))
			.process((exchange) ->
					{exchange.getMessage().setBody(
							Utils.prepareParkEntranceBookingRequest(exchange.getMessage().getBody(AdventureBookingRequest.class)));
					} )
			.marshal(jaxbParkEntrance)
			.to("spring-ws:http://localhost:8081/soap-api/service/park")
			.to("stream:out")
			.unmarshal(jaxbParkEntrance)
			.process((exchange) -> {
				org.bp.park.BookParkEntranceResponse bookParkEntranceResponse =
						exchange.getMessage().getBody(org.bp.park.BookParkEntranceResponse.class);
				String adventureBookingId = exchange.getProperty("adventureBookingId", String.class);
				bookingIdentifierService.assignParkEntranceBookingId(adventureBookingId, bookParkEntranceResponse.getReturn().getId());
				
				exchange.getProperty("paymentRequest", PaymentRequest.class).setBookingId(adventureBookingId);
				exchange.getProperty("paymentRequest", PaymentRequest.class).setParkEntranceBookingId(bookingIdentifierService.getParkEntranceBookingId(adventureBookingId));
			});
		
		final JaxbDataFormat jaxbCar = new JaxbDataFormat(org.bp.BookingInfo.class.getPackage().getName());
		
		from("direct:bookCar").routeId("bookCar")
			.log("bookCar fired")
			.saga()
				.propagation(SagaPropagation.MANDATORY)
				.compensation("direct:cancelCar").option("adventureBookingId", simple("${exchangeProperty.adventureBookingId}"))
			.process((exchange) ->
					{exchange.getMessage().setBody(
					Utils.prepareCarBookingRequest(exchange.getMessage().getBody(AdventureBookingRequest.class)));
					} )
			.marshal(jaxbCar)
			.to("spring-ws:http://localhost:8080/soap-api/service/car")
			.to("stream:out")
			.unmarshal(jaxbCar)
			.process((exchange) -> {
				org.bp.BookingInfo bookCarResponse = exchange.getMessage().getBody(org.bp.BookingInfo.class);
				String adventureBookingId = exchange.getProperty("adventureBookingId", String.class);
				bookingIdentifierService.assignCarBookingId(adventureBookingId, bookCarResponse.getId());
				
				exchange.getProperty("paymentRequest", PaymentRequest.class).setCarBookingId(bookingIdentifierService.getParkEntranceBookingId(adventureBookingId));
			});
		
		from("direct:payment").streamCaching().routeId("payment")
			.log("payment fired")
			.marshal().json()
			.removeHeaders("CamelHttp*")
			.to("rest:post:payment?host=localhost:8083")
			.to("stream:out")
			.unmarshal().json();
		
		from("direct:bookingPayment").streamCaching().routeId("payment/{id}")
			.log("get payment fired")
			.marshal().json()
			.removeHeaders("CamelHttp*")
			.to("rest:get:payment/{id}?host=localhost:8083")
			.to("stream:out")
			.unmarshal().json();
		
        from("direct:cancelCar").routeId("cancelCar")
	        .log("cancelCar fired")
	        .process((exchange) -> {
	        	String adventureBookingId = exchange.getMessage().getHeader("adventureBookingId", String.class);
	        	int carBookingId = bookingIdentifierService.getCarBookingId(adventureBookingId);
	        	org.bp.CancelBookingRequest cancelCarParkEntranceRequest = new org.bp.CancelBookingRequest();
	        	cancelCarParkEntranceRequest.setBookingId(carBookingId);
	        	exchange.getMessage().setBody(cancelCarParkEntranceRequest); 
	        	} )
	        .marshal(jaxbCar)
	        .to("spring-ws:http://localhost:8080/soap-api/service/car")
	        .to("stream:out")
	        .unmarshal(jaxbCar);
        
        from("direct:cancelParkEntrance").routeId("cancelParkEntrance")
        	.log("cancelParkEntrance fired")
        	.process((exchange) -> {
        		String adventureBookingId = exchange.getMessage().getHeader("adventureBookingId", String.class);
        		int parkEntranceBookingId = bookingIdentifierService.getParkEntranceBookingId(adventureBookingId);
        		org.bp.park.CancelBooking cancelBookParkEntranceRequest = new org.bp.park.CancelBooking();
        		cancelBookParkEntranceRequest.setArg0(parkEntranceBookingId);
        		exchange.getMessage().setBody(cancelBookParkEntranceRequest);
        		} )
        .marshal(jaxbParkEntrance)
        .to("spring-ws:http://localhost:8081/soap-api/service/park")
        .to("stream:out")
        .unmarshal(jaxbParkEntrance);

	}

}
