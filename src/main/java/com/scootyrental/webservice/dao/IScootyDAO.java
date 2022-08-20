package com.scootyrental.webservice.dao;

import com.scootyrental.webservice.model.Booking;
import com.scootyrental.webservice.model.Scooty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IScootyDAO {
    List<Scooty> getAvailableScootyAtAnOutlet(UUID outletId);

    Scooty bookAvailableScooty(UUID outletId, UUID scootyId);

    List<Scooty> getParkedScootyAtOutlet(UUID outletId);

    Scooty getScootyById(UUID scootyId);

    Scooty updateScootyStatus(Scooty scooty);
}
