package com.scootyrental.webservice.service;

import com.scootyrental.webservice.model.Booking;
import com.scootyrental.webservice.model.Outlet;
import com.scootyrental.webservice.model.Scooty;

import java.util.List;
import java.util.UUID;

public interface IOutletService {
    List<Outlet> getOutlets();

    List<Scooty> getAvailableScooties(UUID outletId);

    Booking bookAvailableScooty(UUID outletId, UUID scootyId);

    Outlet getOutletById(UUID outletId);

    int getCurrentOutletParking(UUID outletId);

    Booking endRidingScooty(UUID bookingId, UUID outletId);
}
