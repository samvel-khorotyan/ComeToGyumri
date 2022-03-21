package com.example.cometogyumri.dto.restaurantDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantCommentRequest {

    private int id;
    private int userId;
    private int restaurantId;
    private String description;
}
