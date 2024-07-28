package com.calendar.controllers;

import com.calendar.components.Secrets;
import com.calendar.mapper.requestmapper.UserPostRequestMapper;
import com.calendar.mapper.responsemapper.UserResponseMapper;
import com.calendar.requests.AuthRequestBody;
import com.calendar.requests.UserPostRequestBody;
import com.calendar.responses.AuthResponse;
import com.calendar.responses.UserResponse;
import com.calendar.exceptions.GenerateJWTTokenException;
import com.calendar.models.User;
import com.calendar.components.JwtProvider;
import com.calendar.responses.UsersResponse;
import com.calendar.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users/")
@Api(value = "users/", tags = {"Пользователи"})
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UserResponseMapper userResponseMapper;
    private UserPostRequestMapper userPostRequestMapper;

    private JwtProvider jwtProvider;

    private Secrets secrets;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "auth")
    @ApiOperation(
            value = "Генерация токена авторизации по пользователю",
            httpMethod = "POST",
            response = AuthResponse.class
    )
    public AuthResponse auth(@Valid @RequestBody AuthRequestBody request){
        Optional<User> userOptional = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(userOptional.isPresent()) {
            String token = jwtProvider.generateUserToken(userOptional.get());
            return new AuthResponse(token, secrets.getUserActualDays());

        }else{
            throw new GenerateJWTTokenException();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Получение всех пользователей",
            httpMethod = "GET",
            response = UsersResponse.class
    )
    public UsersResponse findAll(){
        return userResponseMapper.entitiesToResponse(userService.findAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Создание пользователя",
            httpMethod = "POST",
            response = UserResponse.class
    )
    public UserResponse createUser(@Valid @RequestBody UserPostRequestBody userPostRequestBody){
            User unsavedUser = userPostRequestMapper.entityFromRequestBody(userPostRequestBody);
            User savedUser = userService.createUser(unsavedUser);
            return userResponseMapper.entityToResponse(savedUser);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    @ApiOperation(
            value = "Получение пользователя по ID",
            httpMethod = "GET",
            response = UserResponse.class
    )
    public UserResponse findById(
            @PathVariable(name = "id") Long id
    ){
        User user = userService.findById(id);
        return userResponseMapper.entityToResponse(user);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    @ApiOperation(
            value = "Удаление пользователя по ID",
            httpMethod = "GET",
            response = UserResponse.class
    )
    public UserResponse deleteUser(
            @PathVariable(name = "id")Long id
    ){
        User user = userService.deleteById(id);
        return userResponseMapper.entityToResponse(user);
    }
}
