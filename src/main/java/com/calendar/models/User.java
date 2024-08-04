package com.calendar.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Accessors(chain = true)
@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
public class User extends BaseEntity implements AuthEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;

    @Size(min = 3)
    @Column(unique = true)
    private String login;

    @Column(columnDefinition = "TEXT")
    String encodedPassword;

    @Column
    @Nullable
    @Size(min = 5, max = 100)
    private String fio;

    @Column
    @Nullable
    @Email
    private String email;

    @Column
    @NotNull
    @Range(min = 5, max = 100)
    private int age;

    @Column
    private boolean status = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.UNKNOWN;

    @Column(nullable = false)
    private Role role = Role.USER;

    @Column(nullable = false)
    @Getter private LocalDateTime created = LocalDateTime.now();

    @Column(nullable = false)
    @Getter private LocalDateTime updated = LocalDateTime.now();

    public User(String login, String encodedPassword) {
        this.login = login;
        this.encodedPassword = encodedPassword;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Event> events;

    @Override
    public String toString(){
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.login, this.encodedPassword);
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getEncodedPassword() {
        return encodedPassword;
    }

    @Override
    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public enum Sex{
        MALE,
        FEMALE,
        UNKNOWN
    }

    public enum Role{
        ADMIN,
        USER
    }
}
