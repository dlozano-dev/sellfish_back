package com.dlozano.app.rest.Models.DTO;

public class ProfilePictureDTO {

    private int userId;
    private String picture;

    public ProfilePictureDTO() {
    }

    public ProfilePictureDTO(int userId, String picture) {
        this.userId = userId;
        this.picture = picture;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "ProfilePictureDTO{" +
                "userId='" + userId + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}