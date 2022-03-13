package com.calendar.data;

import com.calendar.models.Role;
import lombok.Data;

@Data
public class RoleResponse {
    private Integer id;
    private String name;

    public RoleResponse(Role role){
        this.id = role.getId();
        this.name = role.getName();
    }
}
