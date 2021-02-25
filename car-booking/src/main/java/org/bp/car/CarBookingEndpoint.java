package org.bp.car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bp.BookCarRequest;
import org.bp.CancelBookingRequest;
import org.bp.types.BookingInfo;

@org.springframework.stereotype.Service
public class CarBookingEndpoint implements CarBooking {
	
	static final List<String> brands = Arrays.asList("Mazda", "Dacia", "Volvo", "Skoda", "Renault");
	Random rand = new Random();

	@Override
	public BookingInfo bookCar(BookCarRequest payload) throws CarFaultMsg {
		if(payload != null && payload.getCar() != null && !brands.contains(payload.getCar().getBrand())) {
			org.bp.types.Fault carFault = new org.bp.types.Fault();
			carFault.setCode(15);
			carFault.setText("Chosen brand is not available");
			
			CarFaultMsg fault = new CarFaultMsg("Brand of your choice is not available in our offer. Choose from Mazda/Dacia/Volvo/Skoda/Renault brands", carFault);
			throw fault;
		}
		
		BookingInfo response = new BookingInfo();
		response.setId(Math.abs(rand.nextInt()));
		
		if(payload != null && payload.getCar() != null) {
			if("Mazda".equals(payload.getCar().getBrand()))
				response.setCost(1500);
			else if("Dacia".equals(payload.getCar().getBrand()))
				response.setCost(300);
			else if("Volvo".equals(payload.getCar().getBrand()))
				response.setCost(2000);
			else if("Skoda".equals(payload.getCar().getBrand()))
				response.setCost(800);
			else
				response.setCost(500);
		} else
			response.setCost(0);
		
		return response;
	}

	@Override
	public BookingInfo cancelBooking(CancelBookingRequest payload) throws CarFaultMsg {
		// TODO Auto-generated method stub
		return null;
	}

}
