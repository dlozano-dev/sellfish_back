package com.dlozano.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int userId;

    @Column
    private String picture;

    public ProfilePicture() {
    }

    public ProfilePicture(int userId, String picture) {
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