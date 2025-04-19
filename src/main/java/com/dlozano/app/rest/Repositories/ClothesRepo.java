package com.dlozano.app.rest.Repositories;

import com.dlozano.app.rest.Models.DTO.BrandModelDTO;
import com.dlozano.app.rest.Models.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepo extends JpaRepository<Clothes, Long>, JpaSpecificationExecutor<Clothes> {
    @Query("SELECT DISTINCT new com.dlozano.app.rest.Models.DTO.BrandModelDTO(c.brand, c.model) FROM Clothes c")
    List<BrandModelDTO> findFirst250();
}