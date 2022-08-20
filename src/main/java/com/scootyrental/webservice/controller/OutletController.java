package com.scootyrental.webservice.controller;

import com.scootyrental.webservice.model.Booking;
import com.scootyrental.webservice.model.Outlet;
import com.scootyrental.webservice.model.ResponseEnvelope;
import com.scootyrental.webservice.model.Scooty;
import com.scootyrental.webservice.service.IOutletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class OutletController {

    @Autowired
    private IOutletService outletService;

    @GetMapping("/outlets")
    public ResponseEntity<ResponseEnvelope> getOutlets() {
        List<Outlet> retrievedOutletList = outletService.getOutlets();
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setData(retrievedOutletList);
        responseEnvelope.setError(null);
        responseEnvelope.setSuccess(Boolean.TRUE);
        return ResponseEntity.ok(responseEnvelope);
    }

    @GetMapping("/outlets/{outletId}/scooties")
    public ResponseEntity<ResponseEnvelope> getAvailableScootiesOnOutlet(@PathVariable("outletId") UUID outletId) throws IllegalArgumentException {
        if (outletId == null) {
            throw new IllegalArgumentException("Null or empty outlet ID not allowed");
        }
        List<Scooty> availableScootyList = outletService.getAvailableScooties(outletId);
        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setData(availableScootyList);
        responseEnvelope.setError(null);
        responseEnvelope.setSuccess(Boolean.TRUE);
        return ResponseEntity.ok(responseEnvelope);
    }

    @PostMapping("/outlets/{outletId}/scooties/{scootyId}/book")
    public ResponseEntity<Booking> bookAvailableScooties(
            @PathVariable("outletId") UUID outletId,
            @PathVariable("scootyId") UUID scootyId
    ) throws IllegalArgumentException {
        if (outletId == null || scootyId == null) {
            throw new IllegalArgumentException("Null or empty value for outlet ID or scooty ID not allowed");
        }
        Booking booking = outletService.bookAvailableScooty(outletId, scootyId);
        return ResponseEntity.ok(booking);
    }
}
