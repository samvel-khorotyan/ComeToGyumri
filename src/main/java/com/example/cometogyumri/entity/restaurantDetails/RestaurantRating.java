package com.example.cometogyumri.entity.restaurantDetails;

import com.example.cometogyumri.entity.ratingDetails.RatingNumber;
import com.example.cometogyumri.entity.userDetail.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rest_rating")
public class RestaurantRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private RatingNumber ratingNumber;
    @ManyToOne
    private Restaurant restaurant;
}
