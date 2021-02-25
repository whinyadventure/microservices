package org.bp.adventure.model;

public class AdventureBookingRequest {
	private org.bp.park.Person person;
	private org.bp.Car car;
	private org.bp.park.EntranceFee entranceFee;
	private org.bp.payment.PaymentCard paymentCard;
	
	public org.bp.park.Person getPerson() {
		return person;
	}
	public void setPerson(org.bp.park.Person person) {
		this.person = person;
	}
	public org.bp.Car getCar() {
		return car;
	}
	public void setCar(org.bp.Car car) {
		this.car = car;
	}
	public org.bp.park.EntranceFee getEntranceFee() {
		return entranceFee;
	}
	public void setEntranceFee(org.bp.park.EntranceFee entranceFee) {
		this.entranceFee = entranceFee;
	}
	public org.bp.payment.PaymentCard getPaymentCard() {
		return paymentCard;
	}
	public void setPaymentCard(org.bp.payment.PaymentCard paymentCard) {
		this.paymentCard = paymentCard;
	}
	

}
