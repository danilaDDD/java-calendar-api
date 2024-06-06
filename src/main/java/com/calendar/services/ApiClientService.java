package com.calendar.services;

import com.calendar.models.ApiClient;
import com.calendar.repositories.ApiClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApiClientService implements AuthService<ApiClient>{
    private ApiClientRepository repository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApiClient> findByLoginAndPassword(String login, String password){
        List<ApiClient> clients = repository.findAllByLoginAndActiveTrue(login);
        return clients.stream()
                .filter(apiClient -> passwordEncoder.matches(password, apiClient.getEncodedPassword())).findFirst();

    }

    @Override
    public Optional<ApiClient> findByLoginAndEncodedPassword(String login, String encodedPassword) {
        return repository.findApiClientByLoginAndEncodedPasswordAndActiveTrue(login, encodedPassword);
    }

    public ApiClient createApiClient(String login, String password, boolean active){
        ApiClient apiClient = new ApiClient(login, passwordEncoder.encode(password), active);
        return repository.save(apiClient);
    }

    public ApiClient createApiClient(String login, String password) {
        return createApiClient(login, password, true);
    }
}
