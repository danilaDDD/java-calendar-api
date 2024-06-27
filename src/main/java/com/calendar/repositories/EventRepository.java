package com.calendar.repositories;

import com.calendar.models.Event;
import com.calendar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventsByStatus(Event.EventStatus status);

    List<Event> findEventsByUserAndStatus(User user, Event.EventStatus status);

    List<Event> findEventsByUser(User user);

    Event findUserById(Long id);

    List<Event> findEventsByUserId(Long userId);
}