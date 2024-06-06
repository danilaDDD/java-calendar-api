package com.calendar.controllers;

import com.calendar.components.Secrets;
import com.calendar.data.AuthRequest;
import com.calendar.data.AuthResponse;
import com.calendar.data.UserResponse;
import com.calendar.exceptions.BadRequestException;
import com.calendar.exceptions.GenerateJWTTokenException;
import com.calendar.exceptions.NotFoundException;
import com.calendar.models.User;
import com.calendar.components.JwtProvider;
import com.calendar.services.UserService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    private JwtProvider jwtProvider;

    private Secrets secrets;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request){
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(user != null) {
            String token = jwtProvider.generateUserToken(user);
            return new ResponseEntity<>(new AuthResponse(token, secrets.getUserActualDays()), HttpStatus.OK);

        }else{
            throw new GenerateJWTTokenException();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponse> findAll(){

        return userService.findAll().stream()
                .map(UserResponse::new).collect(Collectors.toList());
    }
    // FIXME: API cannot create users with equal logins
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
