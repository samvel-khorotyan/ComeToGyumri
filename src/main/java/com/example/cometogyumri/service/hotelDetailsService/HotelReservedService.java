package com.example.cometogyumri.service.hotelDetailsService;

import com.example.cometogyumri.entity.hotelDetails.Hotel;
import com.example.cometogyumri.entity.hotelDetails.HotelReserved;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.repository.hotelDetailsRepo.HotelReservedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelReservedService {

    private final HotelReservedRepository hotelReservedRepository;
    private  final HotelService hotelService;
    public void save(HotelReserved hotelReserved, User user, Hotel hotel) {
        List<HotelReserved> allByDateDuration = hotelReservedRepository
                .findAllByDateDuration(hotelReserved.getStartDate(), hotelReserved.getRoomNumber());
        if (allByDateDuration.isEmpty()) {
            hotelReserved.setUser(user);
            hotelReserved.setHotel(hotel);
            hotelReservedRepository.save(hotelReserved);
        }
    }
}
