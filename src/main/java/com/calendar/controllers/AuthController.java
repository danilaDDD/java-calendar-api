package com.calendar.controllers;

import com.calendar.data.AuthRequest;
import com.calendar.data.AuthResponse;
import com.calendar.models.User;
import com.calendar.security.JwtProvider;
import com.calendar.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {
    private UserService userService;
    private JwtProvider jwtProvider;

    @Autowired
    public void setUserService(UserService service){
        this.userService = service;
    }

    @Autowired
    public void setJwtProvider(JwtProvider provider){
        this.jwtProvider = provider;
    }

    @GetMapping("/signin")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(user != null) {
            String token = jwtProvider.generateToken(user.getLogin());
            return new ResponseEntity<>(new AuthResponse(token, jwtProvider.getExpirationDays()), HttpStatus.OK);
        }

        return ResponseEntity.status(403).build();
    }
}
