package com.example.cometogyumri.controller.userDetailsController;

import com.example.cometogyumri.dto.userDetailsDto.CreateUserRequest;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.service.userDetailsService.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Value("${come_to_gyumri.upload.path}")
    private String imagePath;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/users")
    public String getAllUser(ModelMap map) {
        List<User> users = userService.findAll();
        map.addAttribute("users", users);
        return "home-v1";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return "login";
    }

    @GetMapping("/addUser")
    public String addUserPage() {
        return "login";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute CreateUserRequest userRequest,
                          @RequestParam("picture") MultipartFile uploadFile) throws IOException {
        User user = modelMapper.map(userRequest, User.class);
        userService.addUserFromUserRequest(user, uploadFile);
        return "login";
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
