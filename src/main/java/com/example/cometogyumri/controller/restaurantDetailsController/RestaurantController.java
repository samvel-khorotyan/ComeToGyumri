package com.example.cometogyumri.controller.restaurantDetailsController;

import com.example.cometogyumri.dto.restaurantDetailsDto.CreateRestaurantRequest;
import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.restaurantDetails.Restaurant;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.restaurantDetailsService.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;

    @Value("${restaurantImages.upload.path}")
    private String imagePath;




    @GetMapping("/restaurants")
    public String restaurantsPage(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "5") int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Restaurant> allRestaurants = restaurantService.findAllRestaurants(pageRequest);
        modelMap.addAttribute("restaurants", allRestaurants);
        int totalPages = allRestaurants.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "restaurants";
    }

    @GetMapping("/deleteRestaurant/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteById(id);
        return "/";
    }

    @GetMapping("/addRestaurant")
    public String addRestaurantPage() {
        return "saveRestaurant";
    }

    @PostMapping("/addRestaurant")
    public String addRestaurant(@ModelAttribute @Valid CreateRestaurantRequest createRestaurantRequest,
                          BindingResult bindingResult,
                          @RequestParam("pictures") MultipartFile[] uploadFiles,
                           @AuthenticationPrincipal CurrentUser  currentUser,ModelMap map) throws IOException {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError allError : bindingResult.getAllErrors()) {
                errors.add(allError.getDefaultMessage());
                map.addAttribute("errors", errors);
            }
            return "saveRestaurant";
        }
        Restaurant restaurant = modelMapper.map(createRestaurantRequest, Restaurant.class);
       restaurantService.addRestaurant(restaurant, uploadFiles,currentUser.getUser());
        return "redirect:/restaurants";
    }

    @GetMapping("/editRestaurant/{id}")
    public String editRestaurantPage(ModelMap map, @PathVariable("id") int id) {
        map.addAttribute("restaurant", restaurantService.findById(id));
        return "saveRestaurant";

    }

    @GetMapping("/restaurantGetImages")
    public @ResponseBody
    byte[] getImage(@RequestParam("restaurantPicName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(imagePath + picName);
        return IOUtils.toByteArray(inputStream);
    }

}
