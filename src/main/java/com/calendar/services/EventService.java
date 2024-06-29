package com.calendar.services;

import com.calendar.exceptions.AccessDeniedForThisUser;
import com.calendar.exceptions.NotFoundException;
import com.calendar.models.Event;
import com.calendar.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    EventRepository repository;

    public List<Event> findByUserId(Long userId){
        return repository.findEventsByUserId(userId);
    }

    public Event findById(Long id){
        return repository.findEventById(id)
                .orElseThrow(() -> new NotFoundException("Событие по данному идентификатору не найдено"));
    }


    public Event save(Event event){
        return repository.save(event);
    }

    public Event update(Long id, Event event, long userId){
         if(findById(id).getUser().getId() != userId)
             throw new AccessDeniedForThisUser();

         event.setUpdated(LocalDateTime.now());
         return repository.save(event);
    }

    public Event delete(Long id, long userId){
        Event event = findById(id);
        if(event != null) {
            if(event.getUser().getId() != userId) {
                throw new AccessDeniedForThisUser();
            }

            repository.delete(event);
        }else
            throw new NotFoundException("event not found by id");

        return event;
    }

    public Event findByIdAndUserId(Long id, long userId) {
        Event event = findById(id);
        if(event.getUser().getId() != userId)
            throw new AccessDeniedForThisUser();

        return event;
    }
}
