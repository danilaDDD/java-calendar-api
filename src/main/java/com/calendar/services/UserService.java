package com.calendar.services;

import com.calendar.models.User;
import com.calendar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
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

    public User findUserByLogin(String login){
        return repository.findUserByLogin(login);
    }

    public User deleteById(Long id){
        User user = findById(id);
        if (user != null) {
            repository.delete(user);
            return user;
        }

        return null;

    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = findUserByLogin(login);
        if (Objects.isNull(u)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }
        return new org.springframework.security.core.userdetails.User(u.getLogin(), u.getPassword(), true, true,
                true, true, new HashSet<>());
    }
}
