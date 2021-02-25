package org.bp.payment.model;

import java.util.Date;

public class PaymentResponse {

	private int transactionId;
	private Date transactionDate;
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
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Date transactionDate) {
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
