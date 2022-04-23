package com.example.cometogyumri.service.hotelDetailsService;

import com.example.cometogyumri.entity.hotelDetails.HotelReserved;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.repository.hotelDetailsRepo.HotelRepository;
import com.example.cometogyumri.repository.hotelDetailsRepo.HotelReservedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelReservedService {

    private final HotelReservedRepository hotelReservedRepository;
    private final HotelRepository hotelRepository;


    public void save(HotelReserved hotelReserved, User user, int id) {
        hotelReserved.setUser(user);
        hotelReserved.setHotel(hotelRepository.getById(id));
        hotelReservedRepository.save(hotelReserved);
    }
}
