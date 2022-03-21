package com.example.cometogyumri.entity.restaurantDetails;

import com.example.cometogyumri.entity.userDetail.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class RestaurantReserved {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne
    private RestaurantTable table;
    private Date startDate;
    private Date endDate;
}
