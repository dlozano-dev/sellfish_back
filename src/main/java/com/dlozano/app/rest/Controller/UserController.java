package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.*;
import com.dlozano.app.rest.Models.DTO.ProfilePictureDTO;
import com.dlozano.app.rest.Models.DTO.ReviewDTO;
import com.dlozano.app.rest.Models.DTO.UpdateEmailDTO;
import com.dlozano.app.rest.Repositories.ClothesRepository;
import com.dlozano.app.rest.Repositories.ProfilePictureRepository;
import com.dlozano.app.rest.Repositories.SaleRepository;
import com.dlozano.app.rest.Repositories.UserRepository;
import com.dlozano.app.rest.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private JwtService jwtService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Public: check if user or email exists
    @GetMapping("/userExists/{email}/{user}")
    public boolean userExists(@PathVariable String email, @PathVariable String user) {
        return userRepository.findAll().stream()
                .anyMatch(i -> i.getEmail().equals(email.trim()) || i.getUsername().equals(user.trim()));
    }

    // Public: user registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userExists(user.getEmail(), user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User or email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // Public: login with JWT token response
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<User> userOpt = userRepository.findByUsername(username.trim());

        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            String token = jwtService.generateToken(userOpt.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token, "userId", userOpt.get().getId()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authenticated");
        }

        String username = authentication.getName();  // from JWT principal
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User u = user.get();
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", u.getId());
            userInfo.put("username", u.getUsername());
            userInfo.put("email", u.getEmail());
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Protected: get username from JWT
    private String getAuthenticatedUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    // Protected: save profile picture
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/saveProfilePicture")
    public String uploadProfilePicture(@RequestBody ProfilePictureDTO profilePictureDTO) {
        int userId = profilePictureDTO.getUserId();
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
    public ResponseEntity<String> getProfilePicture(@PathVariable int userId) {
        Optional<ProfilePicture> profilePicture = profilePictureRepository.findByUserId(userId);

        return profilePicture
                .map(pfp -> ResponseEntity.ok(pfp.getPicture()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No profile picture found for userId: " + userId));
    }

    @GetMapping("/getUsername/{userId}")
    public ResponseEntity<String> getUserName(@PathVariable long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user
                .map(u -> ResponseEntity.ok(u.getUsername()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No username found for userId: " + userId));
    }

    @GetMapping("/getEmail/{userId}")
    public ResponseEntity<?> getEmailByUserId(@PathVariable long userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(user.getEmail()))
                .orElse(ResponseEntity.badRequest().body("User not found"));
    }

    @PostMapping("/updateEmail")
    public ResponseEntity<?> updateEmail(@RequestBody UpdateEmailDTO request) {
        return userRepository.findById(request.getUserId())
                .map(user -> {
                    user.setEmail(request.getEmail());
                    userRepository.save(user);
                    return ResponseEntity.ok("Email updated");
                })
                .orElse(ResponseEntity.badRequest().body("User not found"));
    }

    @GetMapping("/searchUsernames/{prefix}")
    public ResponseEntity<List<String>> searchUsernames(@PathVariable String prefix) {
        List<String> usernames = userRepository.findTop50UsernamesByPrefix(prefix);
        return ResponseEntity.ok(usernames);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById((long) id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        String profilePic = profilePictureRepository.findByUserId(id)
                .map(ProfilePicture::getPicture)
                .orElse(null);
        List<Clothes> clothes = clothesRepository.findByPublisher(user.getId());
        List<Sale> sales = saleRepository.findBySellerIdAndRateIsNotNull(user.getId());

        List<ReviewDTO> reviews = sales.stream().map(sale -> {
            User buyer = userRepository.findById((long) sale.getBuyerId()).orElse(null);
            String buyerPic = profilePictureRepository.findByUserId(sale.getBuyerId())
                    .map(ProfilePicture::getPicture)
                    .orElse(null);

            ReviewDTO dto = new ReviewDTO();
            dto.setId(sale.getId());
            dto.setBuyerId(sale.getBuyerId());
            dto.setSellerId(sale.getSellerId());
            dto.setRate(sale.getRate());
            dto.setReview(sale.getReview());

            if (buyer != null) {
                dto.setBuyerUsername(buyer.getUsername());
            }

            dto.setBuyerProfilePicture(buyerPic);
            return dto;
        }).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("profilePicture", profilePic);
        response.put("clothes", clothes);
        response.put("reviews", reviews);

        return ResponseEntity.ok(response);
    }
}
