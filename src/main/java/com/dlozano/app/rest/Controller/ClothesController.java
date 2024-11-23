package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.ClotheDTO;
import com.dlozano.app.rest.Models.Clothes;
import com.dlozano.app.rest.Models.Wishlist;
import com.dlozano.app.rest.Repo.ClothesRepo;
import com.dlozano.app.rest.Repo.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @CrossOrigin(origins = "http://localhost:5173")
//    @PostMapping(value = "/saveClothe")
//    public boolean saveClothe(@RequestBody ClotheDTO clotheDTO) {
//        try {
//            Clothes clothes = new Clothes(
//                    clotheDTO.getBrand(),
//                    clotheDTO.getModel(),
//                    clotheDTO.getCategory(),
//                    clotheDTO.getPrice(),
//                    clotheDTO.getPublisher()
//            );
//            clothesRepo.save(clothes);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    @GetMapping(value = "/clothes")
    public List<Clothes> getClothes() {
        return clothesRepo.findAll().reversed();
    }

    @GetMapping(value = "/saveClothe/{brand}/{model}/{category}/{price}/{userId}")
    public boolean saveClothe(
        @PathVariable String brand,
        @PathVariable String model,
        @PathVariable String category,
        @PathVariable float price,
        @PathVariable int userId
    ) {
        try {
            Clothes clothes = new Clothes(
                    brand,
                    model,
                    category,
                    price,
                    userId
            );
            clothesRepo.save(clothes);
            return true;
        } catch (Exception e) {
            return false;
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
}
