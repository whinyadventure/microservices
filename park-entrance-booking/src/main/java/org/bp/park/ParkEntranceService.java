package org.bp.park;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.bp.types.BookingInfo;
import org.bp.types.Fault;
import org.bp.types.EntranceFee;
import org.bp.types.ParkEntranceTicket;

@javax.jws.WebService
@org.springframework.stereotype.Service
public class ParkEntranceService{
	
	static final List<String> parks = Arrays.asList("Arcadia", "Badlands", "Canyonlands", "Denali", "Yellowstone");
	Random rand = new Random();
	
	public BookingInfo bookParkEntrance(ParkEntranceTicket parkTicket) throws ParkFaultMsg {
		if (parkTicket != null) {
			EntranceFee fee = parkTicket.getFee();

			if (fee != null && fee.getFrom() != null && fee.getTo() != null &&
				fee.getFrom().toGregorianCalendar().compareTo(fee.getTo().toGregorianCalendar()) == 1) {

				Fault parkFault = new Fault();
				parkFault.setCode(5);
				parkFault.setText("incorrect dates");
				
				ParkFaultMsg fault = new ParkFaultMsg("'From' date can not be later than 'To' date", parkFault);
				throw fault;
				
			} else if(fee != null && !parks.contains(fee.getPark())) {
				Fault parkFault = new Fault();
				parkFault.setCode(10);
				parkFault.setText("park not available");
				
				ParkFaultMsg fault = new ParkFaultMsg("Park of your choice is not available in our offer. Choose from Arcadia/Badlands/Canyonlands/Denali/Yellowstone national parks", parkFault);
				throw fault;
				
			}
		}


		BookingInfo bookingInfo = new BookingInfo();
		bookingInfo.setId(Math.abs(rand.nextInt()));
		
		if(parkTicket != null && parkTicket.getFee() != null && parkTicket.getFee().getFrom() != null && parkTicket.getFee().getTo() != null) {
			EntranceFee fee = parkTicket.getFee();
			DatatypeFactory dtf = null;
			try {
				dtf = DatatypeFactory.newInstance();
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int dur = dtf.newDuration(fee.getTo().toGregorianCalendar().getTimeInMillis() - fee.getFrom().toGregorianCalendar().getTimeInMillis()).getDays();
			bookingInfo.setCost(60*dur);
			
		} else
			bookingInfo.setCost(0);

		return bookingInfo;
	}
	
	public BookingInfo cancelBooking(int bookingId) throws ParkFaultMsg {
		return null;
	}
}
