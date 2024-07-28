package com.calendar.services;

import com.calendar.exceptions.BadRequestException;
import com.calendar.exceptions.NotFoundException;
import com.calendar.models.User;
import com.calendar.repositories.UserRepository;
import com.calendar.requests.UserRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements AuthService<User>{
    private UserRepository repository;
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

    public User findById(Long id){
        return repository.findUserById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Нет пользователя с данным id=%s", id)));
    }

    public User createUser(User unsavedUser){
        String login = unsavedUser.getLogin();
        if(repository.findUserByLogin(login).isPresent())
            throw new BadRequestException("login is exist");

        return repository.save(unsavedUser);
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

    @Override
    public Optional<User> findByLoginAndEncodedPassword(String login, String encodedPassword) {
        return repository.findUserByLoginAndEncodedPassword(login, encodedPassword);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password){
        Optional<User> users = repository.findUserByLogin(login);
        return users.stream()
                .filter(user -> passwordEncoder.matches(password, user.getEncodedPassword())).findFirst();

    }
}
