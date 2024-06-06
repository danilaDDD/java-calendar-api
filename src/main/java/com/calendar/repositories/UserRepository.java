package com.calendar.repositories;

import com.calendar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByLoginAndEncodedPassword(String login, String encodedPassword);
}
