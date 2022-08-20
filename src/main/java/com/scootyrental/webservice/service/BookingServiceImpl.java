package com.scootyrental.webservice.service;

import com.scootyrental.webservice.dao.IBookingDao;
import com.scootyrental.webservice.enums.BookingStatus;
import com.scootyrental.webservice.exceptions.BookingException;
import com.scootyrental.webservice.model.Booking;
import com.scootyrental.webservice.model.Scooty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private IBookingDao bookingDao;

    @Autowired
    private IScootyService scootyService;

    @Override
    public Booking getBookingById(UUID bookingId) {
        return bookingDao.getBookingById(bookingId);
    }

    public Booking addNewBookingForScooty(Scooty scooty) {
        long bookingTS = System.currentTimeMillis();
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID());
        booking.setScootyId(scooty.getScootyId());
        booking.setScootyRegNo(scooty.getRegistrationNo());
        booking.setBookingTS(bookingTS);
        // Using booking expiry as 10 Mins later its value can come from config
        booking.setBookingExpTS(bookingTS + 10 * 60 * 1000);
        booking.setStatus(BookingStatus.BOOKED);

        return bookingDao.addNewBooking(booking);
    }

    @Override
    public Booking startRidingScooty(UUID bookingId) {
        Booking booking = bookingDao.getBookingById(bookingId);
        if (booking == null) {
            throw new BookingException("No booking found with the provided booking ID");
        }
        if (isBookingExpired(booking)) {
            throw new BookingException("Your booking is expired, please book again to ride");
        }

        booking.setStatus(BookingStatus.ACTIVE);
        booking.setRideStartTS(System.currentTimeMillis());
        booking.setBookingExpTS(null);

        return bookingDao.updateBookingStatus(booking);
    }

    @Override
    public Booking updateBookingStatus(Booking booking) {
        return bookingDao.updateBookingStatus(booking);
    }

    private Boolean isBookingExpired(Booking booking) {
        long currentTime = System.currentTimeMillis();

        return booking.getBookingExpTS() < currentTime;
    }

}
