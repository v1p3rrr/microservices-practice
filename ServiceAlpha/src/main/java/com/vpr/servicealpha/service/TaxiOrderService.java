package com.vpr.servicealpha.service;

import com.vpr.servicealpha.models.TaxiOrder;
import com.vpr.servicealpha.repository.TaxiOrderRepository;
import io.jaegertracing.internal.JaegerTracer;
import io.micrometer.core.annotation.Timed;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxiOrderService {

    private TaxiOrderRepository taxiOrderRepository;

    @Autowired
    public TaxiOrderService(TaxiOrderRepository taxiOrderRepository) {
        this.taxiOrderRepository = taxiOrderRepository;
    }

    @Timed("ExtractSingleOrderFromDB")
    public Optional<TaxiOrder> getTaxiOrder(Long id){
        return taxiOrderRepository.findById(id);
    }

    @Timed("ExtractSingleOrderFromDB")
    public TaxiOrder getLastTaxiOrder(){
        return taxiOrderRepository.findFirstByOrderByIdDesc();
    }

    @Timed("ExtractAllOrderFromDB")
    public List<TaxiOrder> getAllTaxiOrders(){
        return taxiOrderRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Timed("AddOrderToDB")
    public Long addTaxiOrder(TaxiOrder taxiOrder){
        return taxiOrderRepository.saveAndFlush(taxiOrder).getId();
    }
}
