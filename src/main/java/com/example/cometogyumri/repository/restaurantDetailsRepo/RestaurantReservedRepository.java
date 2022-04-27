package com.example.cometogyumri.repository.restaurantDetailsRepo;

import com.example.cometogyumri.entity.restaurantDetails.RestaurantReserved;
import com.example.cometogyumri.entity.restaurantDetails.TableNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RestaurantReservedRepository extends JpaRepository<RestaurantReserved, Integer> {
    @Query("select hr from RestaurantReserved hr where :reserveTime between hr.bookingStartTime and hr.bookingEndTime and hr.bookingDate=:reserveDate and hr.tableNumber = :number")
    List<RestaurantReserved> findAllByDateDuration( LocalTime reserveTime,LocalDate reserveDate, TableNumber number);
}
