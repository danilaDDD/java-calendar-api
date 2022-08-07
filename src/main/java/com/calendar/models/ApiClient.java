package com.calendar.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_clients")
public class ApiClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String login;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @Column(nullable = false)
    private LocalDateTime created;

    @Getter
    @Column(nullable = false)
    private LocalDateTime updated;

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean active = true;
}
