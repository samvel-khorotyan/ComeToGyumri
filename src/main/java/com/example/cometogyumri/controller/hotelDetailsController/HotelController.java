package com.example.cometogyumri.controller.hotelDetailsController;

import com.example.cometogyumri.dto.hotelDetailsDto.CreateHotelRequest;
import com.example.cometogyumri.dto.hotelDetailsDto.CreateHotelReservedRequest;
import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.hotelDetails.HotelReserved;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.hotelDetailsService.HotelReservedService;
import com.example.cometogyumri.service.hotelDetailsService.HotelService;
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
public class HotelController {

    private final HotelService hotelService;
    private final ModelMapper modelMapper;
    private final HotelReservedService hotelReservedService;

    @Value("${hotelImagePath.upload.path}")
    private String hotelImagePath;

    @GetMapping("/showAddHotel")
    public String addHotel() {
        return "saveHotel";
    }

    @PostMapping("/addHotel")
    public String addHotel(@ModelAttribute @Valid CreateHotelRequest createHotelRequest,
                           BindingResult bindingResult, ModelMap map,
                           @RequestParam("picture") MultipartFile[] uploadFiles,
                           @AuthenticationPrincipal CurrentUser currentUser) throws IOException {

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError allError : bindingResult.getAllErrors()) {
                errors.add(allError.getDefaultMessage());
                map.addAttribute("errors", errors);
            }
            return "saveHotel";
        }
        Hotel hotel = modelMapper.map(createHotelRequest, Hotel.class);
        hotelService.saveHotel(hotel, uploadFiles, currentUser.getUser());
        return "redirect:/hotels";
    }

    @GetMapping("/hotels")
    public String hotels(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "5") int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Hotel> hotelPage = hotelService.findAllHotels(pageRequest);
        modelMap.addAttribute("hotels", hotelPage);
        int totalPages = hotelPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "hotels";
    }

    @GetMapping("/hotelBooking{id}")
    public String hotelBooking(@PathVariable("id") int id, ModelMap map) {
        map.addAttribute("hotel", hotelService.getById(id));
        return "hotelBooking";
    }

    @PostMapping("/hotelBooking{id}")
    public String hotelBooking(@ModelAttribute CreateHotelReservedRequest createHotelReservedRequest,
                               @AuthenticationPrincipal CurrentUser currentUser,
                               @PathVariable("id") int id) {

        HotelReserved hotelReserved = modelMapper.map(createHotelReservedRequest, HotelReserved.class);
        Hotel hotel = hotelService.getById(id);
        hotelReservedService.save(hotelReserved, currentUser.getUser(), hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/hotelImages")
    public @ResponseBody
    byte[] getImage(@RequestParam("hotelPicName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(hotelImagePath + picName);
        return IOUtils.toByteArray(inputStream);
    }
}
