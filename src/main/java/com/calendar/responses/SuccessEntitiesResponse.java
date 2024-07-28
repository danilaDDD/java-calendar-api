package com.calendar.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SuccessEntitiesResponse<R extends SuccessEntityResponse> {
    private List<R> items;
}
