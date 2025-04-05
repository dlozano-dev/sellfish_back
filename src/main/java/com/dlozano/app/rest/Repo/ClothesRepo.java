package com.dlozano.app.rest.Repo;

import com.dlozano.app.rest.Models.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothesRepo extends JpaRepository<Clothes, Long> {
    Page<Clothes> findAll(Pageable pageable);
}