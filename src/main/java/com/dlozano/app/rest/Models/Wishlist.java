package com.dlozano.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int userId;

    @Column
    private int clotheId;

    public Wishlist() {
    }

    public Wishlist(int userId, int clotheId) {
        this.userId = userId;
        this.clotheId = clotheId;
    }

    public long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getClotheId() {
        return clotheId;
    }

    public void setClotheId(int clotheId) {
        this.clotheId = clotheId;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                ", userIdT=" + userId +
                ", clotheIdT=" + clotheId +
                '}';
    }
}
