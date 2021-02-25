package org.bp.payment;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Date;
import java.util.List;

import org.bp.payment.model.PaymentException;
import org.bp.payment.model.PaymentNotFoundException;
import org.bp.payment.model.PaymentRequest;
import org.bp.payment.model.PaymentResponse;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@org.springframework.web.bind.annotation.RestController
public class PaymentService {
	
    private static List<PaymentResponse> payments = new ArrayList<PaymentResponse>();
	
	@org.springframework.web.bind.annotation.PostMapping("/payment")
	@Operation(
			summary = "payment operation",
			description = "operation for payment",
			responses = {
			@ApiResponse(responseCode = "200",
			description = "OK",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation
			= PaymentResponse.class))}),
			@ApiResponse(responseCode = "400", description = "Bad Request",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation
			= ExceptionResponse.class))})
			})
	public PaymentResponse payment(
			@org.springframework.web.bind.annotation.RequestBody PaymentRequest paymentRequest) {
		
		if (paymentRequest != null && paymentRequest.getAmount() < 0)
			throw new PaymentException("Amount value can not negative");
			
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setTransactionId(payments.size());
        paymentResponse.setTransactionDate(new Date());
        paymentResponse.setCost(paymentRequest.getAmount());
        paymentResponse.setPaymentCard(paymentRequest.getPaymentCard());
        paymentResponse.setBookingId(paymentRequest.getBookingId());
        paymentResponse.setCarBookingId(paymentRequest.getCarBookingId());
        paymentResponse.setParkEntranceBookingId(paymentRequest.getParkEntranceBookingId());
        payments.add(paymentResponse);
        
        return paymentResponse;
	}
	
	@org.springframework.web.bind.annotation.GetMapping("/payment/{id}")
	@Operation(
			summary = "payment information",
			description = "payment information",
			responses = {
			@ApiResponse(responseCode = "200",
			description = "OK",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation
			= PaymentResponse.class))}),
			@ApiResponse(responseCode = "405", description = "Not found",
			content = {@Content(mediaType = "application/json", schema = @Schema(implementation
			= ExceptionResponse.class))})
			})
    public PaymentResponse getPayment(@org.springframework.web.bind.annotation.PathVariable(value = "id") int id) {
        Optional<PaymentResponse> paymentResponse = payments.stream().filter(x -> x.getTransactionId() == id).findAny();
        
        if (!paymentResponse.isPresent()) {
            throw new PaymentNotFoundException("Transaction with id " + id + " not found");
        }
        
        return paymentResponse.get();
    }

}
