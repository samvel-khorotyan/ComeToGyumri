package com.example.cometogyumri.service.hotelDetailsService;

import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.repository.hotelDetailsRepo.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    public final HotelRepository hotelRepository;
    private final HotelPictureService hotelPictureService;

    public void saveHotel(Hotel hotel, MultipartFile[] uploadedFiles, User user) throws IOException {
        hotel.setUser(user);
        hotelRepository.save(hotel);
        hotelPictureService.saveHotelPictures(uploadedFiles, hotel);
    }

    public Page<Hotel> findAllHotels(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }

    public Hotel getById(int hotelId) {
        return hotelRepository.getById(hotelId);
    }
}
