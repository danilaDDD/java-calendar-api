package com.calendar.mapper.requestmapper;

import com.calendar.models.BaseEntity;
import com.calendar.requests.RequestBody;

public interface RequestEntityMapper<E extends BaseEntity, R extends RequestBody> {
    E entityFromRequestBody(R requestBody);
}
