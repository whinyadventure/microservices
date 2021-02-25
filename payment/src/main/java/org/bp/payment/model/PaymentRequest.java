package org.bp.payment.model;

public class PaymentRequest {
	
	private PaymentCard paymentCard;
	private int amount;
	private String bookingId;
	private int carBookingId;
	private int parkEntranceBookingId;
	
	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public int getCarBookingId() {
		return carBookingId;
	}

	public void setCarBookingId(int carBookingId) {
		this.carBookingId = carBookingId;
	}

	public int getParkEntranceBookingId() {
		return parkEntranceBookingId;
	}

	public void setParkEntranceBookingId(int parkEntranceBookingId) {
		this.parkEntranceBookingId = parkEntranceBookingId;
	}

	public PaymentCard getPaymentCard() {
		return paymentCard;
	}
	
	public void setPaymentCard(PaymentCard paymentCard) {
		this.paymentCard = paymentCard;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
