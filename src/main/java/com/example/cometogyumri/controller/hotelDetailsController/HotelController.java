package com.example.cometogyumri.controller.hotelDetailsController;

import com.example.cometogyumri.dto.hotelDetailsDto.CreateHotelRequest;
import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.hotelDetailsService.HotelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final ModelMapper modelMapper;

    @GetMapping("/addHotel")
    public String addHotel() {

        return "addHotel";
    }

    @PostMapping("/addHotel")
    public String addHotel(@ModelAttribute CreateHotelRequest createHotelRequest,
                           @RequestParam("picture") MultipartFile[] uploadFile,
                           @AuthenticationPrincipal CurrentUser currentUser) throws IOException {

        Hotel hotel = modelMapper.map(createHotelRequest, Hotel.class);
        hotelService.saveHotel(hotel, uploadFile, currentUser.getUser());

        return "redirect:/hotels";
    }

    @GetMapping("/hotels")
    public String hotels() {
        return "hotels";
    }
}
