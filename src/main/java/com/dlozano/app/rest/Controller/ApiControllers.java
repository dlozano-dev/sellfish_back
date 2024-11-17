package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.Car;
import com.dlozano.app.rest.Models.User;
import com.dlozano.app.rest.Repo.CarRepo;
import com.dlozano.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CarRepo carRepo;
    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }
    @GetMapping(value = "/cars/{order}")
    public List<Car> getCarsNew(@PathVariable String order) {
        return switch (order) {
            case "desc" -> carRepo.findAll(Sort.by(Sort.Direction.DESC, "price"));
            case "asc" -> carRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
            case "new" -> carRepo.findAll().reversed();
            default -> carRepo.findAll(); // Old case
        };
    }
    @GetMapping(value = "/cars")
    public List<Car> getCarsNewest() {
        return carRepo.findAll().reversed();
    }

    @GetMapping(value = "/cars/{order}/min/{price}")
    public List<Car> getCarsMin(@PathVariable double price, @PathVariable String order) {
        List<Car> cars = switch (order) {
            case "desc" -> carRepo.findAll(Sort.by(Sort.Direction.DESC, "price"));
            case "asc" -> carRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
            case "new" -> carRepo.findAll().reversed();
            default -> carRepo.findAll(); // Old case
        };
        List<Car> carsToReturn = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPrice() >= price) {
                carsToReturn.add(car);
            }
        }
        return carsToReturn;
    }
    @GetMapping(value = "/cars/{order}/max/{price}")
    public List<Car> getCarsMax(@PathVariable double price, @PathVariable String order) {
        List<Car> cars = switch (order) {
            case "desc" -> carRepo.findAll(Sort.by(Sort.Direction.DESC, "price"));
            case "asc" -> carRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
            case "new" -> carRepo.findAll().reversed();
            default ->
                // Old case
                    carRepo.findAll();
        };
        List<Car> carsToReturn = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPrice() <= price) {
                carsToReturn.add(car);
            }
        }
        return carsToReturn;
    }
    @GetMapping(value = "/cars/{order}/min/{minPrice}/max/{maxPrice}")
    public List<Car> getCarsMinMax(@PathVariable double minPrice, @PathVariable double maxPrice, @PathVariable String order) {
        List<Car> cars = switch (order) {
            case "desc" -> carRepo.findAll(Sort.by(Sort.Direction.DESC, "price"));
            case "asc" -> carRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
            case "new" -> carRepo.findAll().reversed();
            default ->
                // Old case
                    carRepo.findAll();
        };
        List<Car> carsToReturn = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPrice() >= minPrice && car.getPrice() <= maxPrice) {
                carsToReturn.add(car);
            }
        }
        return carsToReturn;
    }
    @GetMapping(value = "/userExists/{email}/{user}")
    public boolean userExists(@PathVariable String email, @PathVariable String user) {
        List<User> users = userRepo.findAll();
        for (User i : users) {
            if (i.getEmail().equals(email.trim()) || i.getUsername().equals(user.trim())) {
                return true;
            }
        }
        return false;
    }
    @GetMapping(value = "/getUsers")
    public List<User> getUsers(@RequestBody User user) {
        return userRepo.findAll();
    }
    @GetMapping(value = "/saveUser/{username}/{email}/{password}")
    public void saveUser(@PathVariable String username, @PathVariable String email, @PathVariable String password) {
        User user = new User(username, email, password);
        userRepo.save(user);
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteCar(@PathVariable long id) {
        Car deleteUser = carRepo.findById(id).get();
        carRepo.delete(deleteUser);
        return "Delete car with the id: " + id;
    }
    @GetMapping(value = "/login/{user}/{password}")
    public long login(@PathVariable String user, @PathVariable String password) {
        List<User> users = userRepo.findAll();

        for (User i : users) {
            if (i.getUsername().equals(user.trim()) && i.getPassword().equals(password)) {
                return i.getId();
            }
        }
        return -1;
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
