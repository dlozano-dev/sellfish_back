package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.*;
import com.dlozano.app.rest.Models.DTO.BrandModelDTO;
import com.dlozano.app.rest.Models.DTO.ClotheDTO;
import com.dlozano.app.rest.Repo.ClothesRepo;
import com.dlozano.app.rest.Repo.UserRepository;
import com.dlozano.app.rest.Repo.WishlistRepo;
import com.dlozano.app.rest.Services.ClothesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
public class ClothesController {
    @Autowired
    private ClothesRepo clothesRepo;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClothesService clothesService;

    @Autowired
    private WishlistRepo wishlistRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public Wishlist getByUserId(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new WishlistRowMapper());
    }
    private static class WishlistRowMapper implements RowMapper<Wishlist> {
        @Override
        public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Wishlist wishlist = new Wishlist();
            wishlist.setId((long) rs.getInt("id"));
            wishlist.setUserId((int) rs.getLong("user_id"));
            wishlist.setClotheId((int) rs.getLong("clothe_id"));
            return wishlist;
        }
    }

    @GetMapping(value = "/wishlist/{userId}")
    public List<Clothes> getWishList(@PathVariable long userId) {
        List<Wishlist> wishlist = wishlistRepo.findAll();
        List<Long> ids = new ArrayList<>();
        for (Wishlist i: wishlist) {
            if (i.getUserId() == userId) {
                ids.add(i.getClotheId());
            }
        }
        return clothesRepo.findAllById(ids);
    }

    @GetMapping("/clothes")
    public Page<Clothes> getClothes(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) List<String> sizes,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return clothesService.getFilteredClothes(search, categories, sizes, location, minPrice, maxPrice, sort, pageable);
    }
    @GetMapping("/suggestions")
    public List<BrandModelDTO> getSuggestions() {
        return clothesRepo.findFirst250();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/saveClothe")
    public ResponseEntity<Boolean> saveClothe(@RequestBody ClotheDTO clotheDTO) {
        try {
            // Ensure user exists
            User user = userRepository.findById((long) clotheDTO.getPublisher()).orElseThrow(() ->
                new RuntimeException("User not found")
            );

            Clothes clothes = new Clothes(
                clotheDTO.getBrand(),
                clotheDTO.getModel(),
                clotheDTO.getCategory(),
                clotheDTO.getPrice(),
                user.getId(),
                clotheDTO.getPicture(),
                Long.toString(System.currentTimeMillis()),
                clotheDTO.getSize(),
                clotheDTO.getState(),
                clotheDTO.getLocation()
            );

            clothesRepo.save(clothes);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }

    @GetMapping(value = "/liked/{userId}/{productId}")
    public boolean setFavorite(@PathVariable int userId, @PathVariable int productId) {
        try {
            Wishlist liked = new Wishlist(userId, productId);
            wishlistRepo.save(liked);
            return true;
        } catch (Exception e) {
            String id = "SELECT * FROM wishlist WHERE user_id = ? AND clothe_id = ?";
            wishlistRepo.delete(jdbcTemplate.queryForObject(id, new Object[]{userId, productId}, new WishlistRowMapper()));
            return false;
        }
    }

    @GetMapping(value = "/isFav/{userId}/{productId}")
    public boolean isFavorite(@PathVariable int userId, @PathVariable int productId) {
        try {
            String id = "SELECT * FROM wishlist WHERE user_id = ? AND clothe_id = ?";
            jdbcTemplate.queryForObject(id, new Object[]{userId, productId}, new WishlistRowMapper());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PutMapping("/clothes/{id}")
    public ResponseEntity<?> updateClothe(@PathVariable int id, @RequestBody ClotheDTO updatedClotheDTO) {
        try {
            Clothes updatedClothe = clothesService.updateClothe(id, updatedClotheDTO);
            return ResponseEntity.ok(updatedClothe);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clothe not found with ID: " + id);
        }
    }
}
