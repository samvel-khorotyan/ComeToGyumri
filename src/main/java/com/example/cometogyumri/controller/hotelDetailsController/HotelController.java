package com.example.cometogyumri.controller.hotelDetailsController;

import com.example.cometogyumri.dto.hotelDetailsDto.CreateHotelRequest;
import com.example.cometogyumri.dto.hotelDetailsDto.CreateHotelReservedRequest;
import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.hotelDetails.HotelReserved;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.hotelDetailsService.HotelReservedService;
import com.example.cometogyumri.service.hotelDetailsService.HotelService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final ModelMapper modelMapper;
    private final HotelReservedService hotelReservedService;

    @Value("${hotelImagePath.upload.path}")
    private String hotelImagePath;

    @GetMapping("/addHotel")
    public String addHotel() {
        return "saveHotel";
    }

    @PostMapping("/addHotel")
    public String addHotel(@ModelAttribute CreateHotelRequest createHotelRequest, @RequestParam("picture") MultipartFile[] uploadFiles,
                           @AuthenticationPrincipal CurrentUser currentUser) throws IOException {

        Hotel hotel = modelMapper.map(createHotelRequest, Hotel.class);
        hotelService.saveHotel(hotel, uploadFiles, currentUser.getUser());
        return "hotels1";
    }

    @GetMapping("/hotels")
    public String hotels(ModelMap modelMap) {
        modelMap.addAttribute("hotels", hotelService.findAllHotels());
        return "hotels";
    }

    @GetMapping("/hotelBooking{id}")
    public String hotelBooking(@PathVariable("id") int id, ModelMap map) {
            map.addAttribute("hotel", hotelService.getById(id));
            return "hotelBooking";
    }

    @PostMapping("/hotelBooking{id}")
    public String hotelBooking(@ModelAttribute CreateHotelReservedRequest createHotelReservedRequest,
                               @AuthenticationPrincipal CurrentUser currentUser,
                               @PathVariable("id") int id) {

        HotelReserved hotelReserved = modelMapper.map(createHotelReservedRequest, HotelReserved.class);
        Hotel hotel = hotelService.getById(id);
        hotelReservedService.save(hotelReserved, currentUser.getUser(), hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/hotelImages")
    public @ResponseBody
    byte[] getImage(@RequestParam("hotelPicName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(hotelImagePath + picName);
        return IOUtils.toByteArray(inputStream);
    }
}
