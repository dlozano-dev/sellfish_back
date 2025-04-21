package com.dlozano.app.rest.Repositories;

import com.dlozano.app.rest.Models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findBySellerIdAndRateIsNotNull(int sellerId);

    List<Sale> findByBuyerIdAndRateIsNull(int buyerId);
}

