package com.example.cometogyumri.dto.hotelDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelReservedRequest {

    private int id;
    private int userId;
    private int hotelId;
    private String startDate;
    private String endDate;
    private String roomNumber;
}
