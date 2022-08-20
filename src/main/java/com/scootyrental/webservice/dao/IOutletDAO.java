package com.scootyrental.webservice.dao;

import com.scootyrental.webservice.model.Outlet;

import java.util.List;
import java.util.UUID;

public interface IOutletDAO {
    List<Outlet> getOutlets();

    Outlet getOutletById(UUID outletId);
}
