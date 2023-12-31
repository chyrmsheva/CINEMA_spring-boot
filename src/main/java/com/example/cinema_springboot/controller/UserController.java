package com.example.cinema_springboot.controller;

import com.example.cinema_springboot.model.entity.security.User;
import com.example.cinema_springboot.service.impl.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class UserController {

    UserService userService;

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("user", new User());
        return "/user/save";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/cinema/find_all";
    }
}
