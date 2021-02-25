package org.bp.adventure.model;

import java.time.OffsetDateTime;

public class Utils {
	
	public static org.bp.BookCarRequest prepareCarBookingRequest(AdventureBookingRequest adventureBookingRequest) {
		org.bp.BookCarRequest bookCarRequest =  new org.bp.BookCarRequest();
		bookCarRequest.setCar(adventureBookingRequest.getCar());
		
		org.bp.Person person =  new org.bp.Person();
		if (adventureBookingRequest.getPerson() != null) {
			person.setFirstName(adventureBookingRequest.getPerson().getFirstName());
			person.setLastName(adventureBookingRequest.getPerson().getLastName());
		}
		
		bookCarRequest.setPerson(person);
		
		return bookCarRequest;				
	}
	
	public static org.bp.CancelBookingRequest prepareCarCancelRequest(org.bp.BookingInfo bookingInfo) {
		org.bp.CancelBookingRequest cancelBookingRequest = new org.bp.CancelBookingRequest();
		cancelBookingRequest.setBookingId(bookingInfo.getId());
		
		return cancelBookingRequest;
	}
	
	public static org.bp.park.BookParkEntrance prepareParkEntranceBookingRequest(AdventureBookingRequest adventureBookingRequest) {
		org.bp.park.BookParkEntrance bookParkEntrance = new org.bp.park.BookParkEntrance();
		org.bp.park.ParkEntranceTicket parkEntranceTicket =  new org.bp.park.ParkEntranceTicket();
		
		parkEntranceTicket.setFee(adventureBookingRequest.getEntranceFee());
		parkEntranceTicket.setPerson(adventureBookingRequest.getPerson());
		
		bookParkEntrance.setArg0(parkEntranceTicket);
		
		return bookParkEntrance;
	}
	
	public static org.bp.park.CancelBooking prepareParkEntranceCancelRequest(org.bp.park.BookParkEntranceResponse bookParkEntranceResponse) {
		org.bp.park.CancelBooking cancelBookingRequest = new org.bp.park.CancelBooking();
		cancelBookingRequest.setArg0(bookParkEntranceResponse.getReturn().getId());
		
		return cancelBookingRequest;
	}	

	
	public static org.bp.payment.PaymentRequest preparePaymentRequest(AdventureBookingRequest adventureBookingRequest) {
		org.bp.payment.PaymentRequest paymentRequest = new org.bp.payment.PaymentRequest();
		paymentRequest.setPaymentCard(adventureBookingRequest.getPaymentCard());
		paymentRequest.setAmount(0);
		paymentRequest.setBookingId("");
		paymentRequest.setCarBookingId(-1);
		paymentRequest.setParkEntranceBookingId(-1);
		
		return paymentRequest;
	}
	
	public static org.bp.payment.PaymentResponse createPaymentResponse() {
		org.bp.payment.PaymentResponse paymentResponse = new org.bp.payment.PaymentResponse();
		paymentResponse.setTransactionId(43434);
		paymentResponse.setTransactionDate(OffsetDateTime.now());
		paymentResponse.setCost(0);
		paymentResponse.setBookingId("");
		paymentResponse.setCarBookingId(-1);
		paymentResponse.setParkEntranceBookingId(-1);
		
		return paymentResponse;
	}
}
