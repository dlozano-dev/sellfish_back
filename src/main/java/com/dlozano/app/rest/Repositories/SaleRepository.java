package com.dlozano.app.rest.Repositories;

import com.dlozano.app.rest.Models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}

