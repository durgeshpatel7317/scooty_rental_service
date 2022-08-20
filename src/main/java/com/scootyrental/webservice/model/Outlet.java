package com.scootyrental.webservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Outlet {
    private UUID outletId;
    private String address;
    private Location outletLocation;
    private Integer totalCapacity;
}
