package com.calendar.models;

import com.calendar.constants.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "events")
@Setter
@Getter
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter private Long id;

    @Size(min = 3)
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

    @DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
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
