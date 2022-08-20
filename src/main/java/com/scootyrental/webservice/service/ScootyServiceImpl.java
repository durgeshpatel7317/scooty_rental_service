package com.scootyrental.webservice.service;

import com.scootyrental.webservice.dao.IScootyDAO;
import com.scootyrental.webservice.model.Scooty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScootyServiceImpl implements IScootyService {

    @Autowired
    IScootyDAO scootyDAO;

    @Override
    public List<Scooty> getAvailableScootyAtAnOutlet(UUID outletId) {
        return scootyDAO.getAvailableScootyAtAnOutlet(outletId);
    }

    @Override
    public Scooty bookAvailableScooty(UUID outletId, UUID scootyId) {
        return scootyDAO.bookAvailableScooty(outletId, scootyId);
    }

    @Override
    public List<Scooty> getParkedScootyAtOutlet(UUID outletId) {
        return scootyDAO.getParkedScootyAtOutlet(outletId);
    }

    @Override
    public Scooty getScootyById(UUID scootyId) {
        return scootyDAO.getScootyById(scootyId);
    }

    @Override
    public Scooty updateScootyStatus(Scooty scooty) {
        return scootyDAO.updateScootyStatus(scooty);
    }
}
