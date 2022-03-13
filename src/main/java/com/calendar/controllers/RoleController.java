package com.calendar.controllers;

import com.calendar.data.RoleRequest;
import com.calendar.data.RoleResponse;
import com.calendar.models.Role;
import com.calendar.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRoles(@RequestBody RoleRequest request){
        Role role = request.getInstance();

        if(service.findByName(role.getName()) == null){
            service.save(role);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.status(400).build();
    }
}
