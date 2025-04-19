package com.dlozano.app.rest.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "buyer_id")
    private int buyerId;

    @Column(name = "seller_id")
    private int sellerId;

    @Column(name = "product_id")
    private int productId;

    @Column
    private String rate;

    @Column(length = 500)
    private String review;

    // Constructors
    public Sale() {}

    public Sale(int buyerId, int sellerId, int productId) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.rate = null;
        this.review = null;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
