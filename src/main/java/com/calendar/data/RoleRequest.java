package com.calendar.data;

import com.calendar.models.Role;
import lombok.Data;

@Data
public class RoleRequest {
    String name;

    public Role getInstance(){
        return new Role(name);
    }
}
