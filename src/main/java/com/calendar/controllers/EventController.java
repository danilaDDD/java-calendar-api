package com.calendar.controllers;

import com.calendar.components.AuthUtils;
import com.calendar.components.EventRequestPostBuilder;
import com.calendar.components.EventRequestPutBuilder;
import com.calendar.components.EventsGetResponseFilter;
import com.calendar.requests.EventPostRequest;
import com.calendar.requests.EventPutRequest;
import com.calendar.exceptions.BadRequestException;
import com.calendar.exceptions.NotFoundException;
import com.calendar.models.Event;
import com.calendar.models.User;
import com.calendar.responses.EventResponse;
import com.calendar.services.EventService;
import com.calendar.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events/")
@Api(value = "events/", tags = {"Управление событиями"})
@AllArgsConstructor
public class EventController {
    private EventService eventService;
    private UserService userService;

    private EventRequestPostBuilder eventRequestPostBuilder;
    private EventRequestPutBuilder eventRequestPutBuilder;
    private EventsGetResponseFilter getResponseFilter;
    private AuthUtils authUtils;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Получить все события",
            httpMethod = "GET",
            response = Json.class
    )
    public List<EventResponse> findAll(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "from", required = false) String fromDateString,
            @RequestParam(name = "to", required = false) String toDateString,
            HttpServletRequest request
    )

    {
        long userId = authUtils.getUserId(request);
        List<Event> events = getResponseFilter.filter(status, fromDateString,
                toDateString, userId);
        return serialize(events);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    @ApiOperation(
            value = "Получить событие по id",
            httpMethod = "GET",
            response = Json.class
    )
    public ResponseEntity<EventResponse> findById(
            @PathVariable(name = "id") Long id,
            HttpServletRequest request
    ){
        long userId = authUtils.getUserId(request);
        Event event = eventService.findByIdAndUserId(id, userId);
        if(event != null)
            return new ResponseEntity<>(serialize(eventService.findById(id)), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/status/", method = RequestMethod.GET)
    @ApiOperation(
            value = "Получить список всех допустимых статусов событий",
            httpMethod = "GET",
            response = Json.class
    )
    public List<Event.EventStatus> status(){
        return Arrays.stream(Event.EventStatus.values())
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.PUT)
    @ApiOperation(
            value = "Обновление существующего событие по id",
            httpMethod = "PUT",
            response = Json.class
    )
    public ResponseEntity<EventResponse> putEvent(
            @PathVariable(name="id") Long id,
            @Valid @RequestBody EventPutRequest requestBody,
            HttpServletRequest request
            )
    {
        long userId = authUtils.getUserId(request);
        Event event = eventRequestPutBuilder.build(id, requestBody);
        if(event != null) {
            Event updatedEvent = eventService.update(id, event, userId);
            return new ResponseEntity<>(new EventResponse(updatedEvent), HttpStatus.OK);
        }else
            throw new NotFoundException("Нет событий с таким id");

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Сохранение нового события",
            httpMethod = "POST",
            response = Json.class
    )
    public ResponseEntity<EventResponse> postEvent(
            @Valid @RequestBody EventPostRequest requestBody,
            HttpServletRequest request
            ){
        long userId = authUtils.getUserId(request);
        User user = userService.findById(userId);

        Event event = eventRequestPostBuilder.build(requestBody, user);
        if(event != null) {
            Event savedEvent = eventService.save(event);
            if(savedEvent != null)
                return new ResponseEntity<>(new EventResponse(savedEvent), HttpStatus.CREATED);
        }else
            throw new NotFoundException("Нет события с таким id");



        throw new BadRequestException();
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    @ApiOperation(
            value = "Удаление существующего события по id",
            httpMethod = "GET",
            response = Json.class
    )
    public ResponseEntity<EventResponse> deleteEvent(
            @PathVariable(name = "id") Long id,
            HttpServletRequest request
    ){
        long userId = authUtils.getUserId(request);
        Event deletedEvent = eventService.delete(id, userId);
        if(deletedEvent != null)
            return new ResponseEntity<>(new EventResponse(deletedEvent), HttpStatus.OK);
        else
            throw new NotFoundException();
    }

    public List<EventResponse> serialize(List<Event> events){
        return events.stream().map(EventResponse::new).collect(Collectors.toList());
    }

    private EventResponse serialize(Event event){
            return new EventResponse(event);
    }


}
