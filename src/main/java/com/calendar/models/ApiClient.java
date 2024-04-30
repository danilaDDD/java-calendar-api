package com.calendar.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_clients")
@Getter
public class ApiClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String login;

    @Setter
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime updated;

    @Setter
    @Column(nullable = false)
    private boolean active = true;
}
