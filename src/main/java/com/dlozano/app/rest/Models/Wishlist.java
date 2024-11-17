package com.dlozano.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int userIdT;

    @Column
    private int clotheIdT;

    public Wishlist() {
    }

    public Wishlist(int userIdT, int clotheIdT) {
        this.userIdT = userIdT;
        this.clotheIdT = clotheIdT;
    }

    public int getUserIdT() {
        return userIdT;
    }

    public void setUserIdT(int userIdT) {
        this.userIdT = userIdT;
    }

    public int getClotheIdT() {
        return clotheIdT;
    }

    public void setClotheIdT(int clotheIdT) {
        this.clotheIdT = clotheIdT;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                ", userIdT=" + userIdT +
                ", clotheIdT=" + clotheIdT +
                '}';
    }
}
