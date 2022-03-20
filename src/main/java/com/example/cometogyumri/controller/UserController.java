package com.example.cometogyumri.controller;

import com.example.cometogyumri.dto.CreateUserRequest;
import com.example.cometogyumri.entity.User;
import com.example.cometogyumri.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/users")
    public String getAllUser(ModelMap map){
        List<User> users = userService.findAll();
        map.addAttribute("users",users);
        return "user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @PostMapping("/addUser")
    public String addEmployee(@ModelAttribute CreateUserRequest userRequest,
                              @RequestParam("pictures") MultipartFile uploadFiles) throws IOException {

       userService.addUserFromUserRequest(userRequest,uploadFiles);
        return "redirect:/users";
    }
}
