package com.dlozano.app.rest.Models.DTO;

import lombok.Data;

@Data
public class ReviewDTO {
    private int id;
    private int buyerId;
    private int sellerId;
    private String rate;
    private String review;
    private String buyerUsername;
    private String buyerProfilePicture;
}

