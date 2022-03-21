package com.example.cometogyumri.dto.restaurantDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantTableRequest {

    private int id;
    private String tableName;
    private String description;
    private int restaurantId;

}
