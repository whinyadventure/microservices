package org.bp.adventureclient.model;

import java.time.OffsetDateTime;


public class PaymentResponse {

	private int transactionId;
	private OffsetDateTime transactionDate;
	private PaymentCard paymentCard;
	private int cost;
	private String bookingId;
	private int carBookingId;
	private int parkEntranceBookingId;
	
	public PaymentCard getPaymentCard() {
		return paymentCard;
	}

	public void setPaymentCard(PaymentCard paymentCard) {
		this.paymentCard = paymentCard;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	public OffsetDateTime getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(OffsetDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	
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

}
