package com.dlozano.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String category;
    @Column
    private float price;
    @Column
    private String advertiser;
    @Column
    private String picture;
    public Clothes() {
    }

    public Clothes(String brand, String model, String category, float price, String advertiser, String picture) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.advertiser = advertiser;
        this.picture = picture;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", advertiser='" + advertiser + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
