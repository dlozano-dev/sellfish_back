package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.Car;
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
import java.util.Collections;
import java.util.List;

@RestController
public class ClothesController {
    @Autowired
    private ClothesRepo clothesRepo;

    @Autowired
    private WishlistRepo wishlistRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Wishlist getUserById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new WishlistRowMapper());
    }
    private static class WishlistRowMapper implements RowMapper<Wishlist> {
        @Override
        public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Wishlist wishlist = new Wishlist();
            wishlist.setUserIdT((int) rs.getLong("user_idT"));
            wishlist.setUserIdT((int) rs.getLong("clothe_idT"));
            return wishlist;
        }
    }

    @GetMapping(value = "/wishlist/{userId}")
    public List<Wishlist> getWishList(@PathVariable long userId) {
        return wishlistRepo.findAllById(Collections.singleton(userId));
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "/saveClothe")
    public boolean saveClothe(@RequestBody ClotheDTO clotheDTO) {
        try {
            Clothes clothes = new Clothes(
                    clotheDTO.getBrand(),
                    clotheDTO.getModel(),
                    clotheDTO.getCategory(),
                    clotheDTO.getPrice(),
                    clotheDTO.getAdvertiser()
            );
            clothesRepo.save(clothes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping(value = "/clothes")
    public List<Clothes> getClothes() {
        return clothesRepo.findAll().reversed();
    }

    @GetMapping(value = "/liked/{userId}/{productId}")
    public boolean setWishList(@PathVariable int userId, @PathVariable int productId) {
        try {
            Wishlist liked = new Wishlist(
                userId,
                productId
            );
            wishlistRepo.save(liked);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
