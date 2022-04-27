package com.example.cometogyumri.service.restaurantDetailsService;

import com.example.cometogyumri.entity.restaurantDetails.Restaurant;
import com.example.cometogyumri.entity.restaurantDetails.RestaurantPicture;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.repository.restaurantDetailsRepo.RestaurantPictureRepository;
import com.example.cometogyumri.repository.restaurantDetailsRepo.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantPictureRepository pictureRepository;
    @Value("${restaurantImages.upload.path}")
    private String restaurantImagePath;

    public void addRestaurant(Restaurant restaurant, MultipartFile[] uploadFiles, User user) throws IOException {

        restaurant.setUser(user);
        restaurantRepository.save(restaurant);
        saveRestaurantImages(uploadFiles, restaurant);


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

    private void saveRestaurantImages(MultipartFile[] uploadedFiles, Restaurant restaurant) throws IOException {
        for (MultipartFile uploadFile : uploadedFiles) {
            if (!uploadFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + uploadFile.getOriginalFilename();
                File newFile = new File(restaurantImagePath + fileName);
                uploadFile.transferTo(newFile);
                RestaurantPicture picture = RestaurantPicture.builder()
                        .picUrl(fileName)
                        .restaurant(restaurant)
                        .build();

                pictureRepository.save(picture);
            }
        }
    }

    public Restaurant getRestaurantById(int restaurantId) {
      return restaurantRepository.getById(restaurantId);
    }
}