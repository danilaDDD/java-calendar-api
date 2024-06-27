package com.calendar.services;

import com.calendar.models.Event;
import com.calendar.models.User;
import com.calendar.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    EventRepository repository;

    public List<Event> findAll(){
        return repository.findAll();
    }

    public List<Event> findByUserId(Long userId){
        return repository.findEventsByUserId(userId);
    }

    public List<Event> findByUserAndStatus(User user, Event.EventStatus status){
        return repository.findEventsByUserAndStatus(user, status);
    }

    public Event findById(Long id){
        return repository.findUserById(id);
    }

    public List<Event> findByStatus(Event.EventStatus status){
        return repository.findEventsByStatus(status);
    }

    public Set<Event.EventStatus> statusList(){
        return Arrays.stream(Event.EventStatus.values()).collect(Collectors.toSet());
    }

    public Event save(Event event){
        return repository.save(event);
    }

    public Event update(Long id, Event event){
         event.setUpdated(LocalDateTime.now());
         return repository.save(event);
    }

    public Event delete(Long id){
        Event event = findById(id);
        if(event != null)
            repository.delete(event);

        return event;
    }
}
