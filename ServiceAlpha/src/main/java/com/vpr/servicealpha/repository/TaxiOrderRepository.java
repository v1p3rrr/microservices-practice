package com.vpr.servicealpha.repository;

import com.vpr.servicealpha.models.TaxiOrder;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaxiOrderRepository extends JpaRepository<TaxiOrder, Long> {
    @Timed(value = "\"ExtractSingleOrderFromDB\"")
    @NonNull
    Optional<TaxiOrder> findById(@NonNull Long id);

    @Timed("ExtractAllOrderFromDB")
    @NonNull
    List<TaxiOrder> findAll(@NonNull Sort sort);

    @Timed("AddOrderToDB")
    @NonNull TaxiOrder saveAndFlush(@NonNull TaxiOrder paymentInfo);
}
