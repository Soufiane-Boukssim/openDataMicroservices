package com.soufiane.authservice.controllers;

import com.soufiane.authservice.dtos.user.*;
import com.soufiane.authservice.services.UserService;
import com.soufiane.sharedlibrary.dto.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserRegisterResponse addUser(@RequestBody UserRegisterRequest user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest user) {
        return userService.verify(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(new UserInfoResponse(user.getUsername())); // ou un objet plus complet
    }

}