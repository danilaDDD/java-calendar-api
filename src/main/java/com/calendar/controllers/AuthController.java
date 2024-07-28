package com.calendar.controllers;

import com.calendar.components.Secrets;
import com.calendar.requests.AuthRequestBody;
import com.calendar.responses.AuthResponse;
import com.calendar.exceptions.GenerateJWTTokenException;
import com.calendar.models.ApiClient;
import com.calendar.components.JwtProvider;
import com.calendar.services.ApiClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@Api(value = "get-token/", tags = {"Генерация первичного токена авторизации"})
@AllArgsConstructor
public class AuthController {
    private ApiClientService apiClientService;
    private JwtProvider jwtProvider;
    private Secrets secrets;

    @PostMapping("/get-token/")
    @ApiOperation(
            value = "Генерация первичного токена авторизации",
            httpMethod = "POST",
            response = AuthResponse.class
    )
    public ResponseEntity<AuthResponse> getToken(@Valid @RequestBody AuthRequestBody request) {
        Optional<ApiClient> clientOptional = apiClientService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(clientOptional.isPresent()) {
            String token = jwtProvider.generateClientToken(clientOptional.get());
            return new ResponseEntity<>(new AuthResponse(token, secrets.getApiClientActualDays()), HttpStatus.OK);

        }else{
            throw new GenerateJWTTokenException();
        }
    }
}
