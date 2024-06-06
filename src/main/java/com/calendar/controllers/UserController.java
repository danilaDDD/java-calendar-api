package com.calendar.controllers;

import com.calendar.components.Secrets;
import com.calendar.requests.AuthRequest;
import com.calendar.requests.UserPostRequest;
import com.calendar.responses.AuthResponse;
import com.calendar.responses.UserResponse;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    private JwtProvider jwtProvider;

    private Secrets secrets;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "auth")
    public ResponseEntity<AuthResponse> auth(@Valid @RequestBody AuthRequest request){
        Optional<User> userOptional = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(userOptional.isPresent()) {
            String token = jwtProvider.generateUserToken(userOptional.get());
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
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserPostRequest userPostRequest){
            User savedUser = userService.createUser(userPostRequest);
            return new ResponseEntity<>(new UserResponse(savedUser), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> findById(
            @PathVariable(name = "id") Long id
    ){
        User user = userService.findById(id);
        if (user != null){
            return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
        }else
            throw new NotFoundException("Нет пользователя с данным id");
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public ResponseEntity<UserResponse> deleteUser(
            @PathVariable(name = "id")Long id
    ){
        User user = userService.deleteById(id);
        if(user != null){
            return new ResponseEntity<>(new UserResponse(user), HttpStatus.OK);
        }else
            throw new NotFoundException("Нет пользователя с таким id");
    }
}
