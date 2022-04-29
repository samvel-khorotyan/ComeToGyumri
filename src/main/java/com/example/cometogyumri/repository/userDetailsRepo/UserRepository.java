package com.example.cometogyumri.repository.userDetailsRepo;

import com.example.cometogyumri.entity.userDetail.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public  interface UserRepository extends JpaRepository<User, Integer>  {

    Optional<User> findByEmail(String email);

    Optional<User> findByToken(String token);

    List<User> findAllByName(String name);


}