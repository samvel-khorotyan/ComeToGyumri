package com.example.cometogyumri.service.restaurantDetailsService;

import com.example.cometogyumri.entity.restaurantDetails.RestaurantReserved;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.repository.restaurantDetailsRepo.RestaurantReservedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantReservedService {
    private final RestaurantReservedRepository repository;
    private final RestaurantService restaurantService;


    public void booking(RestaurantReserved reserved, User user, int restaurantId) {
        var allByDateDuration = repository
                .findAllByTimeDuration(reserved.getBookingStartTime(),reserved.getBookingDate(), reserved.getTableNumber());
        if (allByDateDuration.isEmpty()) {
            reserved.setUser(user);
            reserved.setRestaurant(restaurantService.getRestaurantById(restaurantId));
            repository.save(reserved);
        }

    }
}
