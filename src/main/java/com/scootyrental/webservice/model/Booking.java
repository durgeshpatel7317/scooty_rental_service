package com.scootyrental.webservice.model;

import com.scootyrental.webservice.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private UUID bookingId;
    private UUID scootyId;
    private String scootyRegNo;
    private Long bookingTS;
    private Long bookingExpTS; // booking expiry time is 10 mins from booking time, the value for allowed time can be picked from config
    private Long rideStartTS;
    private Long rideEndTS;
    private BookingStatus status;
}
