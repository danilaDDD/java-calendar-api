package com.calendar.repositories;

import com.calendar.models.EventGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventGroupRepository extends JpaRepository<EventGroup, Long> {}
