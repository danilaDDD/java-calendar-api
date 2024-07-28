package com.calendar.mapper.requestmapper;

import com.calendar.exceptions.BadRequestException;
import com.calendar.models.User;
import com.calendar.requests.UserRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserPostRequestMapper implements RequestEntityMapper<User, UserRequestBody>{
    PasswordEncoder passwordEncoder;

    @Override
    public User entityFromRequestBody(UserRequestBody userRequestBody) {
        User.Role role = userRequestBody.getRole();
        User.Sex sex = userRequestBody.getSex();

        return new User(
                userRequestBody.getLogin(),
                passwordEncoder.encode(userRequestBody.getPassword())
        )
                .setAge(userRequestBody.getAge())
                .setStatus(userRequestBody.isStatus())
                .setFio(userRequestBody.getFio())
                .setEmail(userRequestBody.getEmail())
                .setRole(role != null ? role: User.Role.USER)
                .setSex(sex != null ? sex: User.Sex.UNKNOWN);

    }
}
