package com.calendar.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "event_group")
public class EventGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Override
    public boolean equals(Object obj) {
        EventGroup otherEventGroup = (EventGroup) obj;
        return Objects.equals(otherEventGroup.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
