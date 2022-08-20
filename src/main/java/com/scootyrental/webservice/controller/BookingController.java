package com.scootyrental.webservice.controller;

import com.scootyrental.webservice.model.Booking;
import com.scootyrental.webservice.service.IBookingService;
import com.scootyrental.webservice.service.IOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @Autowired
    private IOutletService outletService;

    @PatchMapping("/bookings/{bookingId}/scooties/startride")
    public ResponseEntity<Booking> startRidingBookedScooty(
            @PathVariable("bookingId") UUID bookingId
    ) throws IllegalArgumentException {
        if (bookingId == null) {
            throw new IllegalArgumentException("Null or empty value for booking ID is not allowed");
        }
        Booking booking = bookingService.startRidingScooty(bookingId);
        return ResponseEntity.ok(booking);
    }

    @PatchMapping("/bookings/{bookingId}/scooties/endride")
    public ResponseEntity<Booking> startRidingBookedScooty(
            @PathVariable("bookingId") UUID bookingId,
            @RequestParam("outletId") UUID outletId
    ) throws IllegalArgumentException {
        if (bookingId == null) {
            throw new IllegalArgumentException("Null or empty value for booking ID or outlet ID is not allowed");
        }
        Booking booking = outletService.endRidingScooty(bookingId, outletId);
        return ResponseEntity.ok(booking);
    }
}
