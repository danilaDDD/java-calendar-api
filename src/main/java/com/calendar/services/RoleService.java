package com.calendar.services;

import com.calendar.models.Role;
import com.calendar.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository repository;

    @Autowired
    public void setRoleRepository(RoleRepository repository){
        this.repository = repository;
    }

    public List<Role> findAll(){
        return repository.findAll();
    }

    public Role findByName(String name){
        return repository.findByName(name);
    }

    public Role save(Role role){
        return repository.save(role);
    }
}
