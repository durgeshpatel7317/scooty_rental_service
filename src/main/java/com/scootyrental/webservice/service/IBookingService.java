package com.scootyrental.webservice.service;

import com.scootyrental.webservice.model.Booking;
import com.scootyrental.webservice.model.Scooty;

import java.util.UUID;

public interface IBookingService {

    Booking getBookingById(UUID bookingId);
    Booking addNewBookingForScooty(Scooty scooty);

    Booking startRidingScooty(UUID bookingId);

    Booking updateBookingStatus(Booking booking);
}
