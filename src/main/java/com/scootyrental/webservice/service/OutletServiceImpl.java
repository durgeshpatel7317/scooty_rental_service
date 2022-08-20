package com.scootyrental.webservice.service;

import com.scootyrental.webservice.dao.IOutletDAO;
import com.scootyrental.webservice.enums.BookingStatus;
import com.scootyrental.webservice.enums.ScootyStatus;
import com.scootyrental.webservice.exceptions.BookingException;
import com.scootyrental.webservice.exceptions.ParkingException;
import com.scootyrental.webservice.model.Booking;
import com.scootyrental.webservice.model.Outlet;
import com.scootyrental.webservice.model.Scooty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OutletServiceImpl implements IOutletService {

    @Autowired
    private IOutletDAO outletDAO;

    @Autowired
    private IScootyService scootyService;

    @Autowired
    private IBookingService bookingService;

    @Override
    public List<Outlet> getOutlets() {
        return outletDAO.getOutlets();
    }

    @Override
    public List<Scooty> getAvailableScooties(UUID outletId) {
        return scootyService.getAvailableScootyAtAnOutlet(outletId);
    }

    @Override
    public Booking bookAvailableScooty(UUID outletId, UUID scootyId) {

        Scooty bookedScooty = scootyService.bookAvailableScooty(outletId, scootyId);

        return bookingService.addNewBookingForScooty(bookedScooty);
    }

    @Override
    public Outlet getOutletById(UUID outletId) {
        return outletDAO.getOutletById(outletId);
    }

    @Override
    public int getCurrentOutletParking(UUID outletId) {
        List<Scooty> parkedScooty = scootyService.getParkedScootyAtOutlet(outletId);
        return parkedScooty.size();
    }

    @Override
    public Booking endRidingScooty(UUID bookingId, UUID outletId) {
        Outlet requestedOutlet = this.getOutletById(outletId);
        if (requestedOutlet == null) {
            throw new BookingException("Request outlet is not present or it is closed");
        }

        int currentParkingCount = this.getCurrentOutletParking(outletId);

        if (currentParkingCount == requestedOutlet.getTotalCapacity()) {
            throw new ParkingException("Parking is full at requested outlet, please check another outlet for parking");
        }

        Booking booking = bookingService.getBookingById(bookingId);
        booking.setRideEndTS(System.currentTimeMillis());
        booking.setStatus(BookingStatus.COMPLETED);
        Booking updatedBooking = bookingService.updateBookingStatus(booking);

        Scooty bookedScooty = scootyService.getScootyById(booking.getScootyId());
        bookedScooty.setStatus(ScootyStatus.AVAILABLE);
        bookedScooty.setCurrentOutletId(outletId);

        scootyService.updateScootyStatus(bookedScooty);

        return updatedBooking;
    }

}
