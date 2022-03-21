package com.example.cometogyumri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelCommentRequest {

    private int id;
    private int userId;
    private int hotelId;
    private String description;
}
