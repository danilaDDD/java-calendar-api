package com.calendar.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "events")
@Setter
@Getter
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter private Long id;

    @Column(nullable = false, length = 300)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    @Getter private LocalDateTime created = LocalDateTime.now();

    @Column(nullable = false)
    @Getter private LocalDateTime updated = LocalDateTime.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.ENABLE;

    @Column(nullable = false)
    private LocalDateTime played;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Override
    public String toString(){
        return getName() + " " + getCreated().toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, played);
    }

    public enum EventStatus{
        ENABLE,
        PLAYED,
        CANCELED,
        DISABLE
    }
}
