package com.scootyrental.webservice.dao;

import com.scootyrental.webservice.enums.ScootyStatus;
import com.scootyrental.webservice.exceptions.BookingException;
import com.scootyrental.webservice.model.Scooty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ScootyDAOImpl implements IScootyDAO {

    private final List<Scooty> scootyList = new ArrayList<>();

    @Override
    public List<Scooty> getAvailableScootyAtAnOutlet(UUID outletId) {

        return scootyList.stream()
                .filter(scooty -> scooty.getCurrentOutletId() == outletId && scooty.getStatus() == ScootyStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    @Override
    public Scooty bookAvailableScooty(UUID outletId, UUID scootyId) {
        Scooty foundScooty = scootyList.stream()
                .filter(scooty -> scooty.getScootyId() == scootyId && scooty.getCurrentOutletId() == outletId && scooty.getStatus() == ScootyStatus.AVAILABLE)
                .findFirst().orElse(null);

        if (foundScooty == null) {
            throw new BookingException("Scooty is currently unavailable or already booked by someone else, please book another scooty");
        }

        int foundScootyIndex = scootyList.indexOf(foundScooty);
        foundScooty.setStatus(ScootyStatus.BOOKED);
        scootyList.set(foundScootyIndex, foundScooty);

        return foundScooty;
    }

    @Override
    public List<Scooty> getParkedScootyAtOutlet(UUID outletId) {
        return scootyList.stream()
                .filter(scooty -> scooty.getCurrentOutletId() == outletId && scooty.getStatus() != ScootyStatus.RIDING)
                .collect(Collectors.toList());
    }

    @Override
    public Scooty getScootyById(UUID scootyId) {
        return scootyList.stream()
                .filter(scooty -> scooty.getScootyId() == scootyId)
                .findFirst().get();
    }

    @Override
    public Scooty updateScootyStatus(Scooty scootyToUpdate) {
        Scooty foundScooty = scootyList.stream()
                .filter(scooty -> scooty.getScootyId() == scootyToUpdate.getScootyId())
                .findFirst().orElse(null);

        if (foundScooty == null) {
            throw new NoSuchElementException("Provided scooty is not available, please check the details again");
        }

        int scootyIndex = scootyList.indexOf(foundScooty);
        scootyList.set(scootyIndex, scootyToUpdate);
        return scootyToUpdate;
    }
}
