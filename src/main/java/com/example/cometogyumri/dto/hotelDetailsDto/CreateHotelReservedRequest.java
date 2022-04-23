package com.example.cometogyumri.dto.hotelDetailsDto;

import com.example.cometogyumri.entity.hotelDetails.RoomNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelReservedRequest {

    private int id;
    private int userId;
    private int hotelId;
    private String startDate;
    private String endDate;
    private RoomNumber roomNumber;
}
