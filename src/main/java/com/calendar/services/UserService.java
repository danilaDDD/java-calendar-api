package com.calendar.services;

import com.calendar.models.User;
import com.calendar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> findAll(){
        return active(repository.findAll());
    }

    public User findByLoginAndPassword(String login, String password){
        return repository.findUserByLoginAndPassword(login, password);

    }

    public User findById(Long id){
        return repository.findUserById(id);
    }

    public User createUser(User user){
        return repository.save(user);
    }

    public List<User> active(List<User> users){
        return users.stream().filter(User::isStatus).collect(Collectors.toList());

    }
}
