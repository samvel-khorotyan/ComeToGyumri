package com.example.cometogyumri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {

    private int id;
    private String roomName;
    private String description;
    private int hotelId;
    private double price;
}
