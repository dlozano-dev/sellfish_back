package com.dlozano.app.rest.Repositories;

import com.dlozano.app.rest.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CarRepo extends JpaRepository<Car, Long>{

}
