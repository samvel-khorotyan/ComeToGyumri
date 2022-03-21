package com.example.cometogyumri.service.restaurantDetailsService;

import com.example.cometogyumri.entity.restaurantDetails.Restaurant;
import com.example.cometogyumri.repository.restaurantDetailsRepo.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


    public void deleteById(int id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant findById(int id) {
        return restaurantRepository.getById(id);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
