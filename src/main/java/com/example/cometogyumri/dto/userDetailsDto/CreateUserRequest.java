package com.example.cometogyumri.dto.userDetailsDto;

import com.example.cometogyumri.entity.userDetail.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class  CreateUserRequest {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String nationality;
    private String picUrl;
    private Gender gender;
}
