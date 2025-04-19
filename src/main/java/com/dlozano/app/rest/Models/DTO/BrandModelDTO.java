package com.dlozano.app.rest.Models.DTO;

public class BrandModelDTO {
    private String brand;
    private String model;

    // Constructor
    public BrandModelDTO(String brand, String model) {
        this.brand = brand;
        this.model = model;
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
}
