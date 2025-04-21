package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.Clothes;
import com.dlozano.app.rest.Models.Sale;
import com.dlozano.app.rest.Models.User;
import com.dlozano.app.rest.Repositories.ClothesRepository;
import com.dlozano.app.rest.Repositories.SaleRepository;
import com.dlozano.app.rest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClothesRepository clotheRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerSale(
            @RequestParam int productId,
            @RequestParam int sellerId,
            @RequestParam String buyerUsername
    ) {
        Optional<User> buyerOpt = userRepository.findByUsername(buyerUsername);

        if (buyerOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found");
        }

        User buyer = buyerOpt.get();

        if (buyer.getId() == sellerId) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User cannot buy their own item");
        }

        Sale sale = new Sale();
        sale.setProductId(productId);
        sale.setSellerId(sellerId);
        sale.setBuyerId(buyer.getId());
        sale.setRate(null);
        sale.setReview(null);

        saleRepository.save(sale);

        return ResponseEntity.ok("Sale registered successfully");
    }

    @GetMapping("/unrated/{buyerId}")
    public ResponseEntity<?> getUnratedSalesWithClothe(@PathVariable int buyerId) {
        List<Sale> sales = saleRepository.findByBuyerIdAndRateIsNull(buyerId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Sale sale : sales) {
            Optional<Clothes> clotheOpt = clotheRepository.findById((long) sale.getProductId());
            Optional<User> sellerOpt = userRepository.findById((long) sale.getSellerId());

            if (clotheOpt.isPresent() && sellerOpt.isPresent()) {
                Map<String, Object> map = new HashMap<>();
                map.put("saleId", sale.getId());
                map.put("productId", sale.getProductId());
                map.put("clothe", clotheOpt.get());
                map.put("seller", sellerOpt.get().getUsername());
                result.add(map);
            }
        }

        return ResponseEntity.ok(result);
    }
    @PutMapping("/review/{saleId}")
    public ResponseEntity<String> updateReview(
            @PathVariable int saleId,
            @RequestParam String rate,
            @RequestParam(required = false) String review
    ) {
        Optional<Sale> saleOpt = saleRepository.findById(saleId);
        if (saleOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale not found");

        Sale sale = saleOpt.get();
        if (rate == null || rate.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rate is required");
        }

        sale.setRate(rate);
        sale.setReview(review);
        saleRepository.save(sale);

        return ResponseEntity.ok("Review submitted");
    }
}