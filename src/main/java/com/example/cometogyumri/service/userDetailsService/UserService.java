package com.example.cometogyumri.service.userDetailsService;

import com.example.cometogyumri.entity.userDetail.Role;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.repository.userDetailsRepo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${come_to_gyumri.upload.path}")
    private String imagePath;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void addUserFromUserRequest(User user, MultipartFile uploadedFile) throws IOException {
        if (!uploadedFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
            File newFile = new File(imagePath + fileName);
            uploadedFile.transferTo(newFile);
            user.setPicUrl(fileName);
        }
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public User findById(int id) {
        return userRepository.getById(id);
    }

    public User getById(int id) {
        return userRepository.getById(id);
    }
}



