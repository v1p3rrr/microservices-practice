package com.vpr.servicebeta.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PaymentInfo {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float cost;

    private String currency;

    private String paymentType;

    private Integer commissionPercent; // in %, not float numbers

    public PaymentInfo() {
        this.commissionPercent = 0;
    }

    public PaymentInfo(Float cost, String currency, String paymentType) {
        this.cost = cost;
        this.currency = currency;
        this.paymentType = paymentType;
        this.commissionPercent = 0;
    }

    public PaymentInfo(Long id, Float cost, String currency, String paymentType) {
        this.id = id;
        this.cost = cost;
        this.currency = currency;
        this.paymentType = paymentType;
        this.commissionPercent = 0;
    }

    public PaymentInfo(Long id, Float cost, String currency, String paymentType, Integer commissionPercent) {
        this.id = id;
        this.cost = cost;
        this.currency = currency;
        this.paymentType = paymentType;
        this.commissionPercent = commissionPercent;
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

    public Integer getCommissionPercent() {
        return commissionPercent;
    }

    public void setCommissionPercent(Integer commissionPercent) {
        this.commissionPercent = commissionPercent;
    }

    public Float getPriceWithCommission() {
        Float result = cost + cost*(commissionPercent.floatValue()/100);
        return result;
    }
}
