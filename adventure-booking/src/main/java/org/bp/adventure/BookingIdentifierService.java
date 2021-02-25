package org.bp.adventure;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class BookingIdentifierService {
	HashMap<String, BookingIds> bookingIdsMap =  new HashMap<>();
	
	public String generateBookingId() {
		String bookingID = UUID.randomUUID().toString();
		BookingIds bookingIds = new BookingIds();
		bookingIdsMap.put(bookingID, bookingIds);
		
		return bookingID;
	}
	
	public void assignCarBookingId(String adventureBookingId, int carBookingId) {
		bookingIdsMap.get(adventureBookingId).setCarBookingId(carBookingId);
	}
	
	public void assignParkEntranceBookingId(String adventureBookingId, int parkEntranceBoogingId) {
		bookingIdsMap.get(adventureBookingId).setParkEntranceBoogingId(parkEntranceBoogingId);
	}
	
	public int getCarBookingId(String adventureBookingId) {
		return bookingIdsMap.get(adventureBookingId).getCarBookingId();
	}
	
	public int getParkEntranceBookingId(String adventureBookingId) {
		return bookingIdsMap.get(adventureBookingId).getParkEntranceBoogingId();
	}
	
	public static class BookingIds{
		private int carBookingId;
		private int parkEntranceBoogingId;

		public int getCarBookingId() {
			return carBookingId;
		}
		public void setCarBookingId(int carBookingId) {
			this.carBookingId = carBookingId;
		}
		public int getParkEntranceBoogingId() {
			return parkEntranceBoogingId;
		}
		public void setParkEntranceBoogingId(int parkEntranceBoogingId) {
			this.parkEntranceBoogingId = parkEntranceBoogingId;
		}
	}

}
