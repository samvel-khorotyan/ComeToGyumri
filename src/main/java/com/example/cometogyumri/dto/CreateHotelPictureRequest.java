package com.example.cometogyumri.dto;

import com.example.cometogyumri.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelPictureRequest {

    private int id;
    private String picUrl;
    private Hotel hotel;
}
