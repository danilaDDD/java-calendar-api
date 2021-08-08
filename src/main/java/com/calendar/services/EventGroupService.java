package com.calendar.services;

import com.calendar.models.EventGroup;
import com.calendar.repositories.EventGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventGroupService {

    @Autowired
    EventGroupRepository eventNameRepository;

    @Transactional
    public List<EventGroup> findAll(){
        return (List<EventGroup>)eventNameRepository.findAll();
    }

    @Transactional
    public EventGroup save(EventGroup eventGroup){
        return eventNameRepository.save(eventGroup);
    }
}
