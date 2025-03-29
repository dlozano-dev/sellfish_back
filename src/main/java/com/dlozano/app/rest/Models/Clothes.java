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
    @Column
    private String postDate;
    @Column
    private String size;
    @Column
    private String state;

    public Clothes() {
    }

    public Clothes(String brand, String model, String category, float price, int publisher, String picture, String postDate, String size, String state) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.publisher = publisher;
        this.picture = picture;
        this.postDate = postDate;
        this.size = size;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
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

    @Override
    public String toString() {
        return "Clothes{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", publisher=" + publisher +
                ", picture='" + picture + '\'' +
                ", postDate='" + postDate + '\'' +
                ", size='" + size + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
