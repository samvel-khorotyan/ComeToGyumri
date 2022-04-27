package com.example.cometogyumri.repository.hotelDetailsRepo;

import com.example.cometogyumri.entity.hotelDetails.HotelReserved;
import com.example.cometogyumri.entity.hotelDetails.RoomNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface HotelReservedRepository extends JpaRepository<HotelReserved, Integer> {
    @Query("select hr from HotelReserved hr where :reserveDate between hr.startDate and hr.endDate and hr.roomNumber = :number")
    List<HotelReserved>findAllByDateDuration(Date reserveDate, RoomNumber number);
}
