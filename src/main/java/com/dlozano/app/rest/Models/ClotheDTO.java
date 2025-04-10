package com.dlozano.app.rest.Models;

import jakarta.persistence.Column;

public class ClotheDTO {
    private String brand;
    private String model;
    private String category;
    private Float price;
    private int publisher;
    private String picture;
    private String size;
    private String state;
    private String location;
    private String saleState;

    public ClotheDTO() {
    }

    public ClotheDTO(String brand, String model, String category, Float price, int publisher, String picture, String size, String state, String location, String saleState) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.publisher = publisher;
        this.picture = picture;
        this.size = size;
        this.state = state;
        this.location = location;
        this.saleState = saleState;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSaleState() {
        return saleState;
    }

    public void setSaleState(String saleState) {
        this.saleState = saleState;
    }

    @Override
    public String toString() {
        return "ClotheDTO{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", publisher=" + publisher +
                ", picture='" + picture + '\'' +
                ", size='" + size + '\'' +
                ", state='" + state + '\'' +
                ", location='" + location + '\'' +
                ", saleState='" + saleState + '\'' +
                '}';
    }
}