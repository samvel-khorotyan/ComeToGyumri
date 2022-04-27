package com.example.cometogyumri.entity.restaurantDetails;

import com.example.cometogyumri.entity.userDetail.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurant_reserved")
public class RestaurantReserved {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Restaurant restaurant;
    @JoinColumn(name = "booking_date")
    private LocalDate bookingDate;
    private LocalTime bookingStartTime;
    private LocalTime bookingEndTime;
    @Enumerated(EnumType.STRING)
    private TableNumber tableNumber;
    private int personCount;


}
