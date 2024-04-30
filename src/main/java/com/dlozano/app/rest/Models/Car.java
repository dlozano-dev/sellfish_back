package com.dlozano.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class Car{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    String brand;
    @Column
    String model;
    @Column
    double price;
    @Column
    double hp;
    @Column
    double kms;
    @Column
    int doors;
    @Column
    String category;
    public Car() {
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public double getHp() {
        return hp;
    }

    public double getKms() {
        return kms;
    }

    public int getDoors() {
        return doors;
    }

    public String getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", hp=" + hp +
                ", kms=" + kms +
                ", doors=" + doors +
                ", category='" + category + '\'' +
                '}';
    }
}