package com.vpr.servicebeta.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float cost;

    private String currency;

    private String paymentType;

    public PaymentInfo() {

    }

    public PaymentInfo(Float cost, String currency, String paymentType) {
        this.cost = cost;
        this.currency = currency;
        this.paymentType = paymentType;
    }

    public Long getId() {
        return id;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
