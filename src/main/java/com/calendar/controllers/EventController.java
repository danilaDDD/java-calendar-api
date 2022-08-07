package com.calendar.controllers;

import com.calendar.components.EventRequestPostBuilder;
import com.calendar.components.EventRequestPutBuilder;
import com.calendar.data.EventPostRequest;
import com.calendar.data.EventPutRequest;
import com.calendar.data.EventResponse;
import com.calendar.exceptions.BadRequestException;
import com.calendar.exceptions.NotFoundException;
import com.calendar.interfacies.DateFormatter;
import com.calendar.models.Event;
import com.calendar.models.User;
import com.calendar.security.JwtProvider;
import com.calendar.services.EventService;
import com.calendar.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/events/")
@Api(value = "events/", tags = {"Управление событиями"})
public class EventController {
    private EventService eventService;
    private UserService userService;

    private EventRequestPostBuilder eventRequestPostBuilder;
    private EventRequestPutBuilder eventRequestPutBuilder;

    private DateFormatter dateParser;

    private JwtProvider jwtProvider;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    public void setDateParser(DateFormatter dateParser){
        this.dateParser = dateParser;
    }

    @Autowired
    public void setEventService(EventService eventService){
        this.eventService = eventService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setEventRequestPostBuilder(EventRequestPostBuilder postBuilder){
        this.eventRequestPostBuilder = postBuilder;
    }

    @Autowired
    public void setEventRequestPutBuilder(EventRequestPutBuilder eventRequestPutBuilder){
        this.eventRequestPutBuilder = eventRequestPutBuilder;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Получить все события",
            httpMethod = "GET",
            produces = "",
            response = Json.class
    )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    }
    )
    public List<EventResponse> findAll(
            @RequestParam(name = "status", required = false) String status, 
            @RequestParam(name = "from", required = false) String fromDateString,
            @RequestParam(name = "to", required = false) String toDateString,
            HttpServletRequest request
    )

    {
        User user = jwtProvider.getUserFromRequest(request);
        List<Event> events = eventService.findByUser(user);

        Stream<Event> eventsStream = events.stream();

        if(status != null){
            Event.EventStatus eventStatus = Event.EventStatus.valueOf(status);
            eventsStream = eventsStream.filter(event -> event.getStatus().equals(eventStatus));
        }

        if(fromDateString != null){
            LocalDateTime fromDate = dateParser.parseDateTime(fromDateString);
            eventsStream = eventsStream.filter(event -> event.getPlayed().compareTo(fromDate) > 0);
        }

        if(toDateString != null){
            LocalDateTime toDate = dateParser.parseDateTime(toDateString);
            eventsStream = eventsStream.filter(event -> event.getPlayed().compareTo(toDate) < 0);
        }


        events = eventsStream
                .sorted(Comparator.comparing(Event::getPlayed))
                .collect(Collectors.toList());

        return serialize(events);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET)
    public ResponseEntity<EventResponse> findById(
            @PathVariable(name = "id", required = true) Long id
    ){
        Event event = eventService.findById(id);
        if(event != null)
            return new ResponseEntity<>(serialize(eventService.findById(id)), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/status/", method = RequestMethod.GET)
    public List<Event.EventStatus> status(){
        return Arrays.stream(Event.EventStatus.values()).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.PUT)
    public ResponseEntity<EventResponse> putEvent(
            @PathVariable(name="id", required = true) Long id,
            @RequestBody EventPutRequest requestBody
            )
    {

        Event event = eventRequestPutBuilder.build(id, requestBody);
        if(event != null) {
            Event updatedEvent = eventService.update(id, event);
            return new ResponseEntity<EventResponse>(new EventResponse(updatedEvent), HttpStatus.OK);
        }else
            throw new NotFoundException("Нет событий с таким id");

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResponse> postEvent(
            @RequestBody EventPostRequest requestBody,
            HttpServletRequest request
            ){

        User user = jwtProvider.getUserFromRequest(request);

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
    public ResponseEntity<EventResponse> deleteEvent(@PathVariable(name = "id", required = true) Long id){
        Event deletedEvent = eventService.delete(id);
        if(deletedEvent != null)
            return new ResponseEntity<>(new EventResponse(deletedEvent), HttpStatus.OK);
        else
            throw new NotFoundException();
    }

    public List<EventResponse> serialize(List<Event> events){
        return events.stream().map(EventResponse::new).collect(Collectors.toList());
    }

    public EventResponse serialize(Event event){
        if(event != null)
            return new EventResponse(event);
        else
            return new EventResponse();
    }


}
