package com.scootyrental.webservice.dao;

import com.scootyrental.webservice.model.Booking;

import java.util.UUID;

public interface IBookingDao {
    Booking addNewBooking(Booking booking);

    Booking getBookingById(UUID bookingId);

    Booking updateBookingStatus(Booking booking);
}
