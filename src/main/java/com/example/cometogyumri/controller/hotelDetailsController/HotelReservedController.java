package com.example.cometogyumri.controller.hotelDetailsController;

import com.example.cometogyumri.dto.hotelDetailsDto.CreateHotelReservedRequest;
import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.hotelDetails.HotelReserved;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.hotelDetailsService.HotelCommentService;
import com.example.cometogyumri.service.hotelDetailsService.HotelReservedService;
import com.example.cometogyumri.service.hotelDetailsService.HotelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HotelReservedController {

    private final HotelService hotelService;
    private final ModelMapper modelMapper;
    private final HotelReservedService hotelReservedService;
    private final HotelCommentService commentService;

    @GetMapping("/showHotelBooking{id}")
    public String hotelBooking(@PathVariable("id") int id, ModelMap map) {
        map.addAttribute("comments",commentService.GetAllComments());
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
}
