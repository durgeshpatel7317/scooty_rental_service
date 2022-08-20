package com.scootyrental.webservice.dao;

import com.scootyrental.webservice.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BookingDaoImpl implements IBookingDao {

    private final List<Booking> bookingList = new ArrayList<>();

    @Override
    public Booking addNewBooking(Booking booking) {
        bookingList.add(booking);
        return booking;
    }

    @Override
    public Booking getBookingById(UUID bookingId) {
        return bookingList.stream()
                .filter(booking -> booking.getBookingId() == bookingId)
                .findFirst().orElse(null);
    }

    @Override
    public Booking updateBookingStatus(Booking booking) {
        Booking retrievedBooking = bookingList.stream()
                .filter(bookingObj -> bookingObj.getBookingId() == booking.getBookingId())
                .findFirst().get();
        int retrievedBookingIndex = bookingList.indexOf(retrievedBooking);
        bookingList.set(retrievedBookingIndex, booking);
        return booking;
    }
}
