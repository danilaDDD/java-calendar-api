package com.calendar.responses;

import java.util.List;

public class UsersResponse extends SuccessEntitiesResponse<UserResponse>{

    public UsersResponse(List<UserResponse> items) {
        super(items);
    }
}
