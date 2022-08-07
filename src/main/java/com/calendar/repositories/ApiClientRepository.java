package com.calendar.repositories;

import com.calendar.models.ApiClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiClientRepository extends JpaRepository<ApiClient, Long> {
    public ApiClient findApiClientByLoginAndPasswordAndActiveTrue(String login, String password);

    public ApiClient findApiClientByLoginAndActiveTrue(String login);
}
