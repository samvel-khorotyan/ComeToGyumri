package com.example.cometogyumri.repository.restaurantDetailsRepo;

import com.example.cometogyumri.entity.restaurantDetails.RestaurantComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantCommentRepository extends JpaRepository<RestaurantComment, Integer> {

}
