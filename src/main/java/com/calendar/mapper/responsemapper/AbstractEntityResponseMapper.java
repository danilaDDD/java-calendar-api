package com.calendar.mapper.responsemapper;

import com.calendar.models.BaseEntity;
import com.calendar.responses.SuccessEntitiesResponse;
import com.calendar.responses.SuccessEntityResponse;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractEntityResponseMapper<E extends BaseEntity, R extends SuccessEntityResponse, RS extends SuccessEntitiesResponse<R>>
        implements EntityResponseMapper<E, R, RS>{

    @Override
    public RS entitiesToResponse(List<E> entityList) {
        List<R> responseList = entityList.stream().map(this::entityToResponse).collect(Collectors.toList());
        return this.fromEntityItemList(responseList);
    }
}
