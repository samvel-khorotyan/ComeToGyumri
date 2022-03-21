package com.example.cometogyumri.dto.restaurantDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantReservedRequest {

    private int id;
    private int userId;
    private int restaurantTableId;
    private String startDate;
    private String endDate;
}
