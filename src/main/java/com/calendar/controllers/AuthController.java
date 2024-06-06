package com.calendar.controllers;

import com.calendar.components.Secrets;
import com.calendar.data.AuthRequest;
import com.calendar.data.AuthResponse;
import com.calendar.exceptions.GenerateJWTTokenException;
import com.calendar.models.ApiClient;
import com.calendar.components.JwtProvider;
import com.calendar.services.ApiClientService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@AllArgsConstructor
public class AuthController {
    private ApiClientService apiClientService;
    private JwtProvider jwtProvider;
    private Secrets secrets;

    @PostMapping("/get-token/")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest request) {
        Optional<ApiClient> clientOptional = apiClientService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(clientOptional.isPresent()) {
            String token = jwtProvider.generateClientToken(clientOptional.get());
            return new ResponseEntity<>(new AuthResponse(token, secrets.getApiClientActualDays()), HttpStatus.OK);

        }else{
            throw new GenerateJWTTokenException();
        }
    }
}
