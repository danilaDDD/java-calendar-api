package com.calendar.mapper.responsemapper;

import com.calendar.models.User;
import com.calendar.responses.UserResponse;
import com.calendar.responses.UsersResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResponseMapper extends AbstractEntityResponseMapper<User, UserResponse, UsersResponse>{
    @Override
    public UserResponse entityToResponse(User user) {
        return new UserResponse().setLogin(user.getLogin())
                .setFio(user.getFio())
                .setEmail(user.getEmail())
                .setAge(user.getAge())
                .setStatus(user.isStatus())
                .setSex(user.getSex().toString())
                .setId(user.getId());
    }

    @Override
    public UsersResponse fromEntityItemList(List<UserResponse> entityItemList) {
        return new UsersResponse(entityItemList);
    }
}
