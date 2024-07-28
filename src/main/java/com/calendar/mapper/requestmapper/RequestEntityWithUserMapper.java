package com.calendar.mapper.requestmapper;

import com.calendar.models.BaseEntity;
import com.calendar.models.User;
import com.calendar.requests.RequestBody;

public interface RequestEntityWithUserMapper<E extends BaseEntity, R extends RequestBody> {
    E entityFromRequestBody(R requestBody, User user);
}
