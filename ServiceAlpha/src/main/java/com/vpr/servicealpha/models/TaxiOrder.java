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

    private Float startLongitude;

    private Float startLatitude;

    private String destinationAddress;

    private Float destinationLongitude;

    private Float destinationLatitude;

    public TaxiOrder(String startAddress, Float startLongitude, Float startLatitude, String destinationAddress, Float destinationLongitude, Float destinationLatitude) {
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

    public Float getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Float destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public Float getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Float destinationLatitude) {
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

    public Float getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Float startLongitude) {
        this.startLongitude = startLongitude;
    }

    public Float getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Float latitude) {
        this.startLatitude = latitude;
    }
}
