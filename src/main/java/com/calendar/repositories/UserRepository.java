package com.calendar.repositories;

import com.calendar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);

    User findUserByLoginAndPassword(String login, String password);
}
