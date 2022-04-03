package com.calendar.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    public Role(){}

    public Role(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Role(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Integer id;

    @Column(unique = true)
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @Getter @Setter private List<User> users;
}