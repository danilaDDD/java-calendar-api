package com.calendar.services;

import com.calendar.models.ApiClient;
import com.calendar.repositories.ApiClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ApiClientService {
    private ApiClientRepository repository;

    @Autowired
    public void setRepository(ApiClientRepository repository){
        this.repository = repository;
    }

    public ApiClient findByLoginAndPassword(String login, String password){
        return repository.findApiClientByLoginAndPasswordAndActiveTrue(login, password);
    }

    public ApiClient findByLogin(String login){
        return repository.findApiClientByLoginAndActiveTrue(login);
    }
}
