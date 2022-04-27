package com.example.cometogyumri.entity.restaurantDetails;

import com.example.cometogyumri.entity.userDetail.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Restaurant {



    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;
    private String phone;
    private String address;
    private String description;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    private List<RestaurantPicture>restaurantPictures;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
