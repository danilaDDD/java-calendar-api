package com.calendar.repositories;

import com.calendar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    User findUserByLogin(String login);

    User findUserByLoginAndEncodedPassword(String login, String encodedPassword);
}
