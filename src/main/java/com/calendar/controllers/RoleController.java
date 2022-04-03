package com.calendar.controllers;

import com.calendar.data.RoleResponse;
import com.calendar.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoleController {
    private RoleService service;

    @Autowired
    public void setService(RoleService service){
        this.service = service;
    }

    @GetMapping("/roles")
    public List<RoleResponse> getRoles(){
        return service.findAll().stream().map(RoleResponse::new).collect(Collectors.toList());
    }
}
