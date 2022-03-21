package com.example.cometogyumri.dto.hotelDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelRequest {

    private int id;
    private String name;
    private String phone;
    private String address;
    private String description;
    private int userId;
}
