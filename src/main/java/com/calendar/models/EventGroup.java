package com.calendar.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "event_groups")
public class EventGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter  private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private List<Event> events;

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

}
