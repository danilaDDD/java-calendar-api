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
    EventGroupRepository repository;

    public List<EventGroup> findAll(){
        return (List<EventGroup>) repository.findAll();
    }

    public EventGroup findById(Long id){
        return repository.findEventGroupById(id);
    }

    public EventGroup save(EventGroup eventGroup){
        return repository.save(eventGroup);
    }
}
