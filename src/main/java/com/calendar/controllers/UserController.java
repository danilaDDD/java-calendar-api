package com.calendar.controllers;

import com.calendar.data.UserResponse;
import com.calendar.exceptions.BadRequestException;
import com.calendar.exceptions.NotFoundException;
import com.calendar.models.User;
import com.calendar.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/")
public class UserController {
    @Autowired private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponse> findAll(){

        return userService.findAll().stream()
                .map(UserResponse::new).collect(Collectors.toList());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);

        if(savedUser != null)
            return new ResponseEntity<>(new UserResponse(savedUser), HttpStatus.ACCEPTED);
        else
            throw new BadRequestException();

    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> findById(
            @PathVariable(name = "id", required = true) Long id
    ){
        User user = userService.findById(id);
        if (user != null){
            return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
        }else
            throw new NotFoundException("Нет пользователя с данным id");
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public ResponseEntity<UserResponse> deleteUser(
            @PathVariable(name = "id", required = true)Long id
    ){
        User user = userService.deleteById(id);
        if(user != null){
            return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
        }else
            throw new NotFoundException("Нет пользователя с таким id");
    }
}
