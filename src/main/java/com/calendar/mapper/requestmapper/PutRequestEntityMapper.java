package com.calendar.mapper.requestmapper;

import com.calendar.models.BaseEntity;
import com.calendar.requests.RequestBody;

public interface PutRequestEntityMapper<E extends BaseEntity, R extends RequestBody> {
    E update(E existEntity, R requestBody);
}
