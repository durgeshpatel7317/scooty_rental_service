package com.scootyrental.webservice.service;

import com.scootyrental.webservice.model.Booking;
import com.scootyrental.webservice.model.Scooty;

import java.util.List;
import java.util.UUID;

public interface IScootyService {
    List<Scooty> getAvailableScootyAtAnOutlet(UUID outletId);

    Scooty bookAvailableScooty(UUID outletId, UUID scootyId);

    List<Scooty> getParkedScootyAtOutlet(UUID outletId);

    Scooty getScootyById(UUID scootyId);

    Scooty updateScootyStatus(Scooty scooty);
}
