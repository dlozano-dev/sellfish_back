package com.dlozano.app.rest.Models.DTO;

import com.dlozano.app.rest.Models.Clothes;
import com.dlozano.app.rest.Models.Sale;

import java.util.List;

public class UserProfileDTO {
    private int id;
    private String username;
    private String email;
    private String profilePicture; // base64 string
    private List<Clothes> clothes;
    private List<Sale> reviews;

    public UserProfileDTO() {
    }

    public UserProfileDTO(int id, String username, String email, String profilePicture, List<Clothes> clothes, List<Sale> reviews) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
        this.clothes = clothes;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Clothes> getClothes() {
        return clothes;
    }

    public void setClothes(List<Clothes> clothes) {
        this.clothes = clothes;
    }

    public List<Sale> getReviews() {
        return reviews;
    }

    public void setReviews(List<Sale> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", clothes=" + clothes +
                ", reviews=" + reviews +
                '}';
    }
}

