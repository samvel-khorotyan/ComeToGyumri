package com.example.cometogyumri.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor

public class MainController {

    @Value("$springUser.upload.path")
    private String imagePath;


    @GetMapping("/")
    public String main() {
        return "home-v1";
    }



}
