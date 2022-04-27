package com.example.cometogyumri.dto.restaurantDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantReservedRequest {

    private int id;
    private int restaurantId;
    private String bookingDate;
    private String bookingStartTime;
    private String bookingEndTime;
    private String tableNumber;
    private int personCount;

}
