package com.example.cometogyumri.entity.hotelDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hotel_pic")
public class HotelPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String picUrl;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
