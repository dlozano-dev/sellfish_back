package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.*;
import com.dlozano.app.rest.Repo.ProfilePictureRepository;
import com.dlozano.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

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

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/saveProfilePicture")
    public String uploadProfilePicture(@RequestBody ProfilePictureDTO profilePictureDTO) {
        int  userId = profilePictureDTO.getUserId();
        String newPicture = profilePictureDTO.getPicture();

        Optional<ProfilePicture> existing = profilePictureRepository.findByUserId(userId);

        // If user already have a profile picture, replace it
        if (existing.isPresent()) {
            ProfilePicture existingPfp = existing.get();
            existingPfp.setPicture(newPicture);
            profilePictureRepository.save(existingPfp);
        } else {
            ProfilePicture newPfp = new ProfilePicture(userId, newPicture);
            profilePictureRepository.save(newPfp);
        }

        return newPicture;
    }

    @GetMapping("/profilePicture/{userId}")
    public ResponseEntity<String> getProfilePicture(@PathVariable int  userId) {
        Optional<ProfilePicture> profilePicture = profilePictureRepository.findByUserId(userId);

        return profilePicture
            .map(pfp -> ResponseEntity.ok(pfp.getPicture()))
            .orElseGet(
                () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No profile picture found for userId: " + userId)
            );
    }

    @GetMapping("/getUsername/{userId}")
    public ResponseEntity<String> getUserName(@PathVariable long userId) {
        Optional<User> user = userRepo.findById(userId);
        return user
            .map(u -> ResponseEntity.ok(u.getUsername()))
            .orElseGet(
                () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No username found for userId: " + userId)
            );
    }
}
