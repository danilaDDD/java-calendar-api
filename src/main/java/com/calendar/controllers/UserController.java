package com.calendar.controllers;

import com.calendar.data.UserResponse;
import com.calendar.models.User;
import com.calendar.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {
    @Autowired private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponse> findAll(){
        return userService.findAll().stream()
                .map(UserResponse::new).toList();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);

        if(savedUser != null)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(400).build();

    }
}
