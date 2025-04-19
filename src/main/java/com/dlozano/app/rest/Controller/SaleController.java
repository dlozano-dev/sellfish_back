package com.dlozano.app.rest.Controller;

import com.dlozano.app.rest.Models.Sale;
import com.dlozano.app.rest.Models.User;
import com.dlozano.app.rest.Repositories.SaleRepository;
import com.dlozano.app.rest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserRepository userRepository;

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
}