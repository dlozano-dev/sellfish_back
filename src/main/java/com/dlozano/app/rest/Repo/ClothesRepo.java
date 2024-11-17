package com.dlozano.app.rest.Repo;

import com.dlozano.app.rest.Models.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepo extends JpaRepository<Clothes, Long> {

}