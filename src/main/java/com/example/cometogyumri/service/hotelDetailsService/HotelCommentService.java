package com.example.cometogyumri.service.hotelDetailsService;

import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.hotelDetails.HotelComment;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.repository.hotelDetailsRepo.HotelCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelCommentService {

    private final HotelCommentRepository hotelCommentRepository;

    public void addComment(HotelComment hotelComment, User user, Hotel hotel) {
            hotelComment.setUser(user);
            hotelComment.setHotel(hotel);
            hotelCommentRepository.save(hotelComment);
    }

    public List<HotelComment> GetAllComments() {
        List<HotelComment> comments = hotelCommentRepository.findAll();
        if (!comments.isEmpty()){
            return comments;
        }
        return null;
    }
}
