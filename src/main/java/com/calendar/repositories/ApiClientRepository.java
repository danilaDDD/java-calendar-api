package com.calendar.repositories;

import com.calendar.models.ApiClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApiClientRepository extends JpaRepository<ApiClient, Long> {
    Optional<ApiClient> findApiClientByLoginAndEncodedPasswordAndActiveTrue(String login, String encodedPassword);

    List<ApiClient> findAllByLoginAndActiveTrue(String login);
}
