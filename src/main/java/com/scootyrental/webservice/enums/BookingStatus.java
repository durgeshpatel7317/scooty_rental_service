package com.scootyrental.webservice.enums;

public enum BookingStatus {
    BOOKED, ACTIVE, EXPIRED, COMPLETED
    // BOOKED is set when booking is done but not riding yet
    // ACTIVE status is set when ride is ongoing for the booked scooty
}
