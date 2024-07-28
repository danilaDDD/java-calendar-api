package com.calendar.mapper.responsemapper;

import com.calendar.models.BaseEntity;
import com.calendar.responses.SuccessEntitiesResponse;
import com.calendar.responses.SuccessEntityResponse;

import java.util.List;

public interface EntityResponseMapper<E extends BaseEntity, R extends SuccessEntityResponse,
        RS extends SuccessEntitiesResponse<R>> {

        R entityToResponse(E entity);

        RS entitiesToResponse(List<E> entityList);
}
