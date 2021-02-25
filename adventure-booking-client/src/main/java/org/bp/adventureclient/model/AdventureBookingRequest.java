package org.bp.adventureclient.model;


public class AdventureBookingRequest {
	private Person person;
	private Car car;
	private EntranceFee entranceFee;
	private PaymentCard paymentCard;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public EntranceFee getEntranceFee() {
		return entranceFee;
	}
	public void setEntranceFee(EntranceFee entranceFee) {
		this.entranceFee = entranceFee;
	}
	public PaymentCard getPaymentCard() {
		return paymentCard;
	}
	public void setPaymentCard(PaymentCard paymentCard) {
		this.paymentCard = paymentCard;
	}
	

}
