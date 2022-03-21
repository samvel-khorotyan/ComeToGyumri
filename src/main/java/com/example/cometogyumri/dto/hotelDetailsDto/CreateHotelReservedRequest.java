package com.example.cometogyumri.dto.hotelDetailsDto;

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
    private int roomId;
    private Date startDate;
    private Date endDate;
}
