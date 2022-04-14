package com.example.cometogyumri.dto.restaurantDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantRequest {

    private int id;
    private String name;
    private String phone;
    private String address;
    private String description;

}
