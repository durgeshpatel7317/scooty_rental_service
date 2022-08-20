package com.scootyrental.webservice.handler;

import com.scootyrental.webservice.exceptions.BookingException;
import com.scootyrental.webservice.exceptions.ParkingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        Map<String, Object> response = Map.of("success", false, "error", illegalArgumentException.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        Map<String, Object> response = Map.of("success", false, "error", noSuchElementException.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BookingException.class)
    protected ResponseEntity<Object> handleBookingException(BookingException bookingException) {
        Map<String, Object> response = Map.of("success", false, "error", bookingException.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ParkingException.class)
    protected ResponseEntity<Object> handleParkingException(ParkingException parkingException) {
        Map<String, Object> response = Map.of("success", false, "error", parkingException.getMessage());
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception exception) {
        Map<String, Object> response = Map.of("success", false, "error", exception.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

}
