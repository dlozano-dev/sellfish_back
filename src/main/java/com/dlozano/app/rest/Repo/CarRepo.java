package com.dlozano.app.rest.Repo;

import com.dlozano.app.rest.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CarRepo extends JpaRepository<Car, Long>{

}
