package com.dlozano.app.rest.Models;

public class ClotheDTO {
    private String brand;
    private String model;
    private String category;
    private Float price;
    private int publisher;
    private String picture;

    public ClotheDTO() {
    }

    public ClotheDTO(String brand, String model, String category, Float price, int publisher, String picture) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.publisher = publisher;
        this.picture = picture;
    }

    // Getters y Setters
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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
}