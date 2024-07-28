package com.calendar.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_clients")
@Getter
@NoArgsConstructor
public class ApiClient extends BaseEntity implements AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Setter
    private String encodedPassword;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime updated;

    @Setter
    @Column(nullable = false)
    private boolean active = true;

    public ApiClient(String login, String encodedPassword, boolean active) {
        this.login = login;
        this.encodedPassword = encodedPassword;
        this.active = active;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ApiClient{" +
                "active=" + active +
                ", created=" + created +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", updated=" + updated +
                '}';
    }
}
