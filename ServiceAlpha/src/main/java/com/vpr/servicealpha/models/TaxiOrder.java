package com.vpr.servicealpha.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class TaxiOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startAddress;

    private Long startLongitude;

    private Long startLatitude;

    private String destinationAddress;

    private Long destinationLongitude;

    private Long destinationLatitude;

    public TaxiOrder(String startAddress, long startLongitude, long startLatitude, String destinationAddress, long destinationLongitude, long destinationLatitude) {
        this.startAddress = startAddress;
        this.startLongitude = startLongitude;
        this.startLatitude = startLatitude;
        this.destinationAddress = destinationAddress;
        this.destinationLongitude = destinationLongitude;
        this.destinationLatitude = destinationLatitude;
    }

    public TaxiOrder() {

    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Long getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Long destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public Long getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Long destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Long getId() {
        return id;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String address) {
        this.startAddress = address;
    }

    public Long getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Long startLongitude) {
        this.startLongitude = startLongitude;
    }

    public Long getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Long latitude) {
        this.startLatitude = latitude;
    }
}
