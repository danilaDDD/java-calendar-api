package com.calendar.services;

import com.calendar.models.User;
import com.calendar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder encoder){
        this.passwordEncoder = encoder;
    }

    @Autowired
    public void setUserRepository(UserRepository repository){
        this.repository = repository;
    }

    public List<User> findAll(){
        return active(repository.findAll());
    }

    public User findByLoginAndPassword(String login, String password){
        return repository.findUserByLoginAndEncodedPassword(login, this.passwordEncoder.encode(password));

    }

    public User findById(Long id){
        return repository.findUserById(id);
    }

    public User createUser(User user){
        user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
        return repository.save(user);
    }

    public List<User> active(List<User> users){
        return users.stream().filter(User::isStatus).collect(Collectors.toList());

    }

    public User deleteById(Long id){
        User user = findById(id);
        if (user != null) {
            repository.delete(user);
            return user;
        }

        return null;

    }
}
