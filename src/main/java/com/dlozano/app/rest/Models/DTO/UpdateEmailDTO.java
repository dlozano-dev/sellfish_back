package com.dlozano.app.rest.Models.DTO;

public class UpdateEmailDTO {
    private long userId;
    private String email;

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

