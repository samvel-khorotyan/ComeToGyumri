package com.example.cometogyumri.controller.restaurantDetailsController;

import com.example.cometogyumri.dto.restaurantDetailsDto.CreateRestaurantRequest;
import com.example.cometogyumri.entity.restaurantDetails.Restaurant;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.restaurantDetailsService.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;

    @Value("${restaurantImages.upload.path}")
    private String imagePath;




    @GetMapping("/restaurants")
    public String getAllRestaurant(ModelMap map) {
        List<Restaurant> restaurants = restaurantService.findAll();
        map.addAttribute("restaurants", restaurants);
        return "home-v1";
    }

    @GetMapping("/deleteRestaurant/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteById(id);
        return "/";
    }

    @GetMapping("/addRestaurant")
    public String addRestaurantPage() {
        return "login";
    }

    @PostMapping("/addRestaurant")
    public String addRestaurant(@ModelAttribute CreateRestaurantRequest createRestaurantRequest,
                          @RequestParam("pictures") MultipartFile[] uploadFiles,
                           @AuthenticationPrincipal CurrentUser  currentUser) throws IOException {
        Restaurant restaurant = modelMapper.map(createRestaurantRequest, Restaurant.class);
       restaurantService.addRestaurant(restaurant, uploadFiles,currentUser.getUser());
        return "/saveRestaurant";
    }

    @GetMapping("/editRestaurant/{id}")
    public String editRestaurantPage(ModelMap map, @PathVariable("id") int id) {
        map.addAttribute("restaurant", restaurantService.findById(id));
        return "saveRestaurant";

    }

//    @GetMapping("/getImages")
//    public @ResponseBody
//    byte[] getImage(@RequestParam("picName") String picName) throws IOException {
//        InputStream inputStream = new FileInputStream(imagePath + picName);
//        return IOUtils.toByteArray(inputStream);
//    }

}
