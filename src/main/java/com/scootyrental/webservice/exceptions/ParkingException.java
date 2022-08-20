package com.scootyrental.webservice.exceptions;

public class ParkingException extends RuntimeException {
    public ParkingException() {
        super();
    }

    public ParkingException(String message) {
        super(message);
    }
}
