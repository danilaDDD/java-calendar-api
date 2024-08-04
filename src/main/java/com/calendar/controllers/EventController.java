package com.calendar.controllers;

import com.calendar.components.AuthUtils;
import com.calendar.components.EventsGetResponseFilter;
import com.calendar.mapper.requestmapper.EventPostRequestMapper;
import com.calendar.mapper.requestmapper.EventPutRequestMapper;
import com.calendar.mapper.responsemapper.EventResponseMapper;
import com.calendar.requests.EventPostRequestBody;
import com.calendar.requests.EventPutRequestBody;
import com.calendar.models.Event;
import com.calendar.models.User;
import com.calendar.responses.EventResponse;
import com.calendar.responses.EventsResponse;
import com.calendar.services.EventService;
import com.calendar.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events/")
@Api(value = "events/", tags = {"События"})
@AllArgsConstructor
public class EventController {
    private EventService eventService;
    private UserService userService;
    private EventResponseMapper eventResponseMapper;

    private EventPostRequestMapper eventPostRequestMapper;
    private EventPutRequestMapper eventPutRequestMapper;
    private EventsGetResponseFilter getResponseFilter;
    private AuthUtils authUtils;

    //@FIXME сделать отдельные классы запросов списков объектов
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Получить все события",
            httpMethod = "GET",
            response = EventsResponse.class
    )
    public EventsResponse findAll(
            @ApiParam(name = "Статус события", value = "Статус события", example = "ENABLE")
            @RequestParam(name = "status", required = false) Event.EventStatus status,

            @ApiParam(name="Дата начиная с" , value = "Дата и время", example = "01-12-2024 00:12")
            @RequestParam(name = "from", required = false) String fromDateString,

            @ApiParam(name="Дата до" , value = "Дата и время", example = "01-12-1996 00:12")
            @RequestParam(name = "to", required = false) String toDateString,

            HttpServletRequest request
    )

    {
        long userId = authUtils.getUserId(request);
        List<Event> events = getResponseFilter.filter(status, fromDateString,
                toDateString, userId);
        return eventResponseMapper.entitiesToResponse(events);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    @ApiOperation(
            value = "Получить событие по ID",
            httpMethod = "GET",
            response = EventResponse.class
    )
    public EventResponse findById(
            @PathVariable(name = "id") Long id,
            HttpServletRequest request
    ){
        long userId = authUtils.getUserId(request);
        Event event = eventService.findByIdAndUserId(id, userId);
        return eventResponseMapper.entityToResponse(event);
    }

    @RequestMapping(value = "/status/", method = RequestMethod.GET)
    @ApiOperation(
            value = "Получить список всех допустимых статусов событий",
            httpMethod = "GET",
            response = EventResponse.class
    )
    public List<Event.EventStatus> status(){
        return Arrays.stream(Event.EventStatus.values())
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.PUT)
    @ApiOperation(
            value = "Обновление существующего событие по id",
            httpMethod = "PUT",
            response = EventResponse.class
    )
    public EventResponse putEvent(
            @PathVariable(name="id") Long id,
            @Valid @RequestBody EventPutRequestBody requestBody,
            HttpServletRequest request
            )
    {
        long userId = authUtils.getUserId(request);
        Event existEvent = eventService.findById(id);
        Event event = eventPutRequestMapper.update(existEvent, requestBody);
        Event updatedEvent = eventService.update(id, event, userId);
        return eventResponseMapper.entityToResponse(updatedEvent);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Сохранение нового события",
            httpMethod = "POST",
            response = EventResponse.class
    )
    public EventResponse postEvent(
            @Valid @RequestBody EventPostRequestBody requestBody,
            HttpServletRequest request
            ){
        long userId = authUtils.getUserId(request);
        User user = userService.findById(userId);

        Event event = eventPostRequestMapper.entityFromRequestBody(requestBody, user);
        Event savedEvent = eventService.save(event);
        return eventResponseMapper.entityToResponse(savedEvent);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    @ApiOperation(
            value = "Удаление существующего события по id",
            httpMethod = "GET",
            response = EventResponse.class
    )
    public EventResponse deleteEvent(
            @PathVariable(name = "id") Long id,
            HttpServletRequest request
    ){
        long userId = authUtils.getUserId(request);
        Event deletedEvent = eventService.delete(id, userId);
        return eventResponseMapper.entityToResponse(deletedEvent);
    }
}
