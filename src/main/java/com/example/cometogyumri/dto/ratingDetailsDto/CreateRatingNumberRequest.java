package com.example.cometogyumri.dto.ratingDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRatingNumberRequest {
    private int id;
    private int ratingNumber;
}
