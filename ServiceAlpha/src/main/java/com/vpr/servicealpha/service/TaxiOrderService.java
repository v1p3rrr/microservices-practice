package com.vpr.servicealpha.service;

import com.vpr.servicealpha.models.TaxiOrder;
import com.vpr.servicealpha.repository.TaxiOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxiOrderService {

    private TaxiOrderRepository taxiOrderRepository;

    @Autowired
    public TaxiOrderService(TaxiOrderRepository taxiOrderRepository) {
        this.taxiOrderRepository = taxiOrderRepository;
    }

    public Optional<TaxiOrder> getTaxiOrder(Long id){
        return taxiOrderRepository.findById(id);
    }

    public Long addTaxiOrder(TaxiOrder taxiOrder){
        return taxiOrderRepository.saveAndFlush(taxiOrder).getId();
    }
}
