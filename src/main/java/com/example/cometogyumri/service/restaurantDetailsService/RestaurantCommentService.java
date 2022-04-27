package com.example.cometogyumri.service.restaurantDetailsService;

import com.example.cometogyumri.entity.restaurantDetails.Restaurant;
import com.example.cometogyumri.entity.restaurantDetails.RestaurantComment;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.repository.restaurantDetailsRepo.RestaurantCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantCommentService {
    private final RestaurantCommentRepository restaurantCommentRepository;


    public void addComment(RestaurantComment comment, User user,Restaurant restaurant) {
        comment.setUser(user);
        comment.setRestaurant(restaurant);
        restaurantCommentRepository.save(comment);

    }

    public List<RestaurantComment> GetAllComments() {
        List<RestaurantComment> comments = restaurantCommentRepository.findAll();
        if (!comments.isEmpty()){
            return comments;
        }return null;
    }
}
