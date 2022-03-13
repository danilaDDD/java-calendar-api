package com.calendar.repositories;

import com.calendar.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    List<Role> findAll();
    Role save(Role role);
}
