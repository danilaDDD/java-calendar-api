package com.calendar.controllers;

import com.calendar.data.AuthRequest;
import com.calendar.data.AuthResponse;
import com.calendar.exceptions.GenerateJWTTokenException;
import com.calendar.models.ApiClient;
import com.calendar.security.JwtProvider;
import com.calendar.services.ApiClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {
    private ApiClientService apiClientService;
    private JwtProvider jwtProvider;

    @Autowired
    public void setApiClientService(ApiClientService service){
        apiClientService = service;
    }

    @Autowired
    public void setJwtProvider(JwtProvider provider){
        this.jwtProvider = provider;
    }

    @PostMapping("/get-token/")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest request) {
        ApiClient client = apiClientService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if(client != null) {
            String token = jwtProvider.generateClientToken(client.getLogin());
            return new ResponseEntity<>(new AuthResponse(token, jwtProvider.getExpirationDays()), HttpStatus.OK);

        }else{
            throw new GenerateJWTTokenException();
        }
    }
}
