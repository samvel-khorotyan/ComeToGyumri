package com.example.cometogyumri.controller.userDetailsController;

import com.example.cometogyumri.dto.userDetailsDto.CreateUserRequest;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.service.userDetailsService.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Value("${springUser.upload.path}")
    private String imagePath;


    @GetMapping("/users")
    public String getAllUser(ModelMap map) {
        List<User> users = userService.findAll();
        map.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/addUser")
    public String addUserPage() {
        return "saveUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute CreateUserRequest userRequest,
                          @RequestParam("picture") MultipartFile uploadFile) throws IOException {

        userService.addUserFromUserRequest(userRequest, uploadFile);
        return "redirect:/users";
    }

    @GetMapping("/editUser/{id}")
    public String editUserPage(ModelMap map, @PathVariable("id") int id) {
        map.addAttribute("user", userService.findById(id));
        return "saveUser";

    }

    @GetMapping("/getImage")
    public @ResponseBody
    byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(imagePath + picName);
        return IOUtils.toByteArray(inputStream);
    }
}
