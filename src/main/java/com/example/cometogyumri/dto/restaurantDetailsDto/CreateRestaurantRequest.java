package com.example.cometogyumri.dto.restaurantDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantRequest {

    private int id;
    @NotNull(message = "Խնդրում եմ գրեք ռեստորանի անունը։Այն չի կարող դատարկ լինել")
    private String name;
    @NotNull(message = "Խնդրում եմ գրեք  հեռախոսահամարը։Այն չի կարող դատարկ լինել")
    @Size(min = 5, max = 20, message = "սիմվոլները չպետք է պակաս լինեն 5-ից և չպետք է գերազանցեն 20")
    private String phone;
    @NotEmpty(message = "Խնդրում եմ գրեք հասցեն։Այն չի կարող դատարկ լինել")
    private String address;
    private String description;

}
