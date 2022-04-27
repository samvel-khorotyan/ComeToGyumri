package com.example.cometogyumri.controller.restaurantDetailsController;

import com.example.cometogyumri.dto.restaurantDetailsDto.CreateRestaurantReservedRequest;
import com.example.cometogyumri.entity.restaurantDetails.RestaurantReserved;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.restaurantDetailsService.RestaurantCommentService;
import com.example.cometogyumri.service.restaurantDetailsService.RestaurantReservedService;
import com.example.cometogyumri.service.restaurantDetailsService.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class RestaurantReservedController {

    private final RestaurantReservedService reservedService;
    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;
    private final RestaurantCommentService commentService;

@GetMapping("/restaurantReservation{id}")
    public String reservationPage(@PathVariable ("id") int restaurantId, ModelMap map){
    map.addAttribute("comments",commentService.GetAllComments());
    map.addAttribute("restaurant",restaurantService.getRestaurantById(restaurantId));
    return "restaurantReservation";
}
    @PostMapping("/reservationRestaurant{id}")
    public String addReservation(@ModelAttribute CreateRestaurantReservedRequest reservedRequest,
                                 @AuthenticationPrincipal CurrentUser currentUser,
                                 @PathVariable ("id") int restaurantId){

        RestaurantReserved reserved = modelMapper.map(reservedRequest, RestaurantReserved.class);
        reserved.setBookingDate(LocalDate.parse(reservedRequest.getBookingDate()));
        reserved.setBookingStartTime(LocalTime.parse(reservedRequest.getBookingStartTime()));
        reserved.setBookingEndTime(LocalTime.parse(reservedRequest.getBookingEndTime()));
        reservedService.booking(reserved,currentUser.getUser(),restaurantId);

     return "redirect:/restaurants";
    }

}
