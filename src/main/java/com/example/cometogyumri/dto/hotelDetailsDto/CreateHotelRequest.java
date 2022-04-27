package com.example.cometogyumri.dto.hotelDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelRequest {

    private int id;
    @NotEmpty(message = "Հյուրանոցի անուն դաշտը չի կարող դատարկ լինել")
    private String name;
    @NotEmpty(message = "Հյուրանոցի հեռաղոսահամար դաշտը չի կարող դատարկ լինել")
    @Size(min = 2, max = 40, message = "սիմվոլները չպետք է պակաս լինեն 2-ից և չպետք է գերազանցեն 40")
    private String phone;
    @NotEmpty(message = "Հյուրանոցի հասցե դաշտը չի կարող դատարկ լինել")
    private String address;
    private String description;
    private int userId;
}
