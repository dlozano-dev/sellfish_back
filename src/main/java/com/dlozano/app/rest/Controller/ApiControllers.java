package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.Car;
import com.dlozano.app.rest.Repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private CarRepo carRepo;
    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value = "/cars/asc")
    public List<Car> getCarsAsc() {
        return carRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }
    @GetMapping(value = "/cars/desc")
    public List<Car> getCarsDesc() {
        return carRepo.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }
    @GetMapping(value = "/cars")
    public List<Car> getCarsNewest() {
        return carRepo.findAll();
    }

    @GetMapping(value = "/cars/min/{price}")
    public List<Car> getCarsMin(@PathVariable double price) {
        List<Car> cars = carRepo.findAll();
        List<Car> carsToReturn = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPrice() >= price) {
                carsToReturn.add(car);
            }
        }
        return carsToReturn;
    }
    @GetMapping(value = "/cars/max/{price}")
    public List<Car> getCarsMax(@PathVariable double price) {
        List<Car> cars = carRepo.findAll();
        List<Car> carsToReturn = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPrice() <= price) {
                carsToReturn.add(car);
            }
        }
        return carsToReturn;
    }
    @GetMapping(value = "/cars/min/{minPrice}/max/{maxPrice}")
    public List<Car> getCarsMinMax(@PathVariable double minPrice, @PathVariable double maxPrice) {
        List<Car> cars = carRepo.findAll();
        List<Car> carsToReturn = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPrice() >= minPrice && car.getPrice() <= maxPrice) {
                carsToReturn.add(car);
            }
        }
        return carsToReturn;
    }
    @PostMapping(value = "/save")
    public String saveUser(@RequestBody Car car) {
        carRepo.save(car);
        return "Saved...";
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteCar(@PathVariable long id) {
        Car deleteUser = carRepo.findById(id).get();
        carRepo.delete(deleteUser);
        return "Delete car with the id: " + id;
    }

//    @PostMapping(value = "/save")
//    public String saveUser(@RequestBody User user) {
//        userRepo.save(user);
//        return "Saved...";
//    }
//
//    @PutMapping(value = "/update/{id}")
//    public String updateUser(@PathVariable long id, @RequestBody User user) {
//        User updatedUser = userRepo.findById(id).get();
//        updatedUser.setFirstName(user.getFirstName());
//        updatedUser.setSecondName(user.getSecondName());
//        updatedUser.setOccupation(user.getOccupation());
//        updatedUser.setAge(user.getAge());
//        userRepo.save(updatedUser);
//        return "Updated...";
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    public String deleteUser(@PathVariable long id) {
//        User deleteUser = userRepo.findById(id).get();
//        userRepo.delete(deleteUser);
//        return "Delete user with the id: " + id;
//    }
}
