package com.example.cometogyumri.service.hotelDetailsService;

import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.repository.hotelDetailsRepo.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class HotelService {

    public final HotelRepository hotelRepository;
    private final HotelPictureService hotelPictureService;


    public void saveHotel(Hotel hotel, MultipartFile[] uploadedFiles, User user) throws IOException {
        hotel.setUser(user);
        Hotel hotel1 = hotelRepository.save(hotel);
        hotelPictureService.saveHotelPictures(uploadedFiles, hotel1);

    }
}
