package com.example.cometogyumri.controller.restaurantDetailsController;

import com.example.cometogyumri.dto.restaurantDetailsDto.CreateRestaurantCommentRequest;
import com.example.cometogyumri.entity.restaurantDetails.Restaurant;
import com.example.cometogyumri.entity.restaurantDetails.RestaurantComment;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.restaurantDetailsService.RestaurantCommentService;
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

@Controller
@RequiredArgsConstructor
public class RestaurantCommentController {


    private final RestaurantCommentService commentService;
    private final RestaurantService restaurantService;
    private final ModelMapper mapper;





    @PostMapping("/restaurantComment{id}")
    public String restaurantComment( @PathVariable("id") int restaurantId,@ModelAttribute CreateRestaurantCommentRequest createRestaurantCommentRequest,
                                    @AuthenticationPrincipal CurrentUser currentUser) {
        RestaurantComment comment = mapper.map(createRestaurantCommentRequest, RestaurantComment.class);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        commentService.addComment(comment, currentUser.getUser(), restaurant);
        return "redirect:/restaurantReservation{id}";
    }


}
