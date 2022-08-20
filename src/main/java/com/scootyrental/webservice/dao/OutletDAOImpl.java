package com.scootyrental.webservice.dao;

import com.scootyrental.webservice.model.Outlet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OutletDAOImpl implements IOutletDAO {

    private final List<Outlet> outletList = new ArrayList<>();

    @Override
    public List<Outlet> getOutlets() {
        return outletList;
    }

    @Override
    public Outlet getOutletById(UUID outletId) {
        return outletList.stream()
                .filter(outlet -> outlet.getOutletId() == outletId)
                .findFirst()
                .orElse(null);
    }
}
