package com.example.cometogyumri.controller.hotelDetailsController;

import com.example.cometogyumri.dto.hotelDetailsDto.CreateHotelCommentRequest;
import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.hotelDetails.HotelComment;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.hotelDetailsService.HotelCommentService;
import com.example.cometogyumri.service.hotelDetailsService.HotelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HotelCommentController {

    private final HotelCommentService hotelCommentService;
    private final HotelService hotelService;
    private final ModelMapper mapper;

    @PostMapping("/hotelComment{id}")
    public String hotelComment(@PathVariable("id") int hotelId, @ModelAttribute CreateHotelCommentRequest createHotelCommentRequest,
                                    @AuthenticationPrincipal CurrentUser currentUser) {
        HotelComment hotelComment = mapper.map(createHotelCommentRequest, HotelComment.class);
        Hotel hotel = hotelService.getById(hotelId);
        hotelCommentService.addComment(hotelComment, currentUser.getUser(), hotel);
        return "redirect:/showHotelBooking{id}";
    }
}
