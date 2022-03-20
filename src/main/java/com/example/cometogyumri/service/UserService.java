package com.example.cometogyumri.service;


import com.example.cometogyumri.dto.CreateUserRequest;
import com.example.cometogyumri.entity.User;
import com.example.cometogyumri.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Value("${springUser.upload.path}")
    private String imagePath;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void addUserFromUserRequest(CreateUserRequest userRequest, MultipartFile uploadedFiles) throws IOException {
        User user = getUserFromRequest(userRequest);
        userRepository.save(user);
      //  saveUserImages(uploadedFiles, user);
    }

    private void saveUserImages(MultipartFile uploadedFiles) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + uploadedFiles.getOriginalFilename();
        File newFile = new File(imagePath + fileName);
        uploadedFiles.transferTo(newFile);
    }

    private User getUserFromRequest(CreateUserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .nationality(userRequest.getNationality())
                .gender(userRequest.getGender())
                .build();
    }
}



