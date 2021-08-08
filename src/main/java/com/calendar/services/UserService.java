package com.calendar.services;

import com.calendar.data.UserResponse;
import com.calendar.models.User;
import com.calendar.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return active(userRepository.findAll());
    }

    public User findByLoginAndPassword(String login, String password){
        return userRepository.findUserByLoginAndPassword(login, password);

    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> active(List<User> users){
        return users.stream().filter(User::isStatus).toList();

    }
}
