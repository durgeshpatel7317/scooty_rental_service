package com.scootyrental.webservice.model;

import com.scootyrental.webservice.enums.ScootyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scooty {
    private UUID scootyId; // Unique Id assigned to each of the scooter
    private UUID currentOutletId; // Outlet where the scooty is parked or available currently, it will change when parked at another outlet
    private String make;
    private String model;
    private String registrationNo;
    private Long registrationDate; // Using milliseconds date
    private Long expiryDate; // Using milliseconds date
    private ScootyStatus status;
    private Double kmsDriven;
    private Location currentLocation;
}
