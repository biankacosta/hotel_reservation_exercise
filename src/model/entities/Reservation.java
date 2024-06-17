package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private int roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(int roomNumber, Date checkIn, Date checkOut) {
		if (checkIn.after(checkOut)) {
			throw new DomainException("Check-out must be after check-in dates.");
		}
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public void updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates por updates must be for future dates.");
		}
		if (checkIn.after(checkOut)) {
			throw new DomainException("Check-out must be after check-in dates.");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room " +
				roomNumber +
				", check-in: " +
				sdf.format(checkIn) +
				", check-out: " +
				sdf.format(checkOut) +
				", " +
				duration() +
				" nights.";
		
	}

}
