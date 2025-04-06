package com.dlozano.app.rest.Repo;

import com.dlozano.app.rest.Models.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothesRepo extends JpaRepository<Clothes, Long>, JpaSpecificationExecutor<Clothes> {

}