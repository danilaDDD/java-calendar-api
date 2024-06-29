package com.calendar.repositories;

import com.calendar.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventById(Long id);

    List<Event> findEventsByUserId(Long userId);
}