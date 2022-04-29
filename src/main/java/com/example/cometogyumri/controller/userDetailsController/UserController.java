package com.example.cometogyumri.controller.userDetailsController;

import com.example.cometogyumri.dto.userDetailsDto.CreateUserRequest;
import com.example.cometogyumri.entity.userDetail.User;
import com.example.cometogyumri.security.CurrentUser;
import com.example.cometogyumri.service.userDetailsService.MailService;
import com.example.cometogyumri.service.userDetailsService.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final MailService mailService;

    @Value("${come_to_gyumri.upload.path}")
    private String imagePath;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String getAllUser(ModelMap map, @AuthenticationPrincipal CurrentUser currentUser) {
        List<User> users = userService.findAll();
        map.addAttribute("users", users);
        map.addAttribute("currentUser", currentUser.getUser());
        return "home-v1____";
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
    public String addUser(@ModelAttribute CreateUserRequest userRequest, Locale locale,
                          @RequestParam("picture") MultipartFile uploadFile) throws IOException, MessagingException {
        User user = modelMapper.map(userRequest, User.class);

        user.setActive(false);
        user.setToken(UUID.randomUUID().toString());
        user.setTokenCreatedDate(LocalDateTime.now());

        userService.addUserFromUserRequest(user, uploadFile);
        mailService.sendHtmlEmail(user.getEmail(), "Welcome " + user.getSurname(), user,
                " http://localhost:8080/user/activate?token=" + user.getToken(), "verifyTemplate", locale);

        return "redirect:/";
    }

    @GetMapping("/user/activate")
    public String activateUser(ModelMap map, @RequestParam String token) {
        Optional<User> user = userService.findByToken(UUID.fromString(token));
        if (user.isEmpty()) {
            map.addAttribute("message", "User Does not exists");
            return "activateUser";
        }
        User userFromDb = user.get();
        if (userFromDb.isActive()) {
            map.addAttribute("message", "User already active!");
            return "activateUser";
        }

        userFromDb.setActive(true);
        userFromDb.setToken(null);
        userFromDb.setTokenCreatedDate(null);
        userService.save(userFromDb);
        map.addAttribute("message", "User activated, please login!");
        return "activateUser";
    }

    @GetMapping("/editUser/{id}")
    public String editUserPage(ModelMap map, @PathVariable("id") int id) {
        map.addAttribute("user", userService.findById(id));
        return "saveUser";
    }

        @GetMapping("/userProfile")
    public String userProfile(ModelMap modelMap,  @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("user",userService.getById(currentUser.getUser().getId()));
        return "myProfile";
    }


    @GetMapping("/getImage")
    public @ResponseBody
    byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(imagePath + picName);
        return IOUtils.toByteArray(inputStream);
    }
}