package com.dlozano.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private String category;
    @Column
    private float price;
    @Column
    private int publisher;
    @Column
    private String picture;
    public Clothes() {
    }

    public Clothes(String brand, String model, String category, float price, int publisher) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.publisher = publisher;
//        this.picture = picture; TODO
    }

    public int getId() {
        return id;
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

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
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
                ", advertiser='" + publisher + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
