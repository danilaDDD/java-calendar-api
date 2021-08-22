package com.calendar.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Accessors(chain = true)
@Setter @Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    @Nullable
    private String fio;

    @Column
    @Nullable
    private String email;

    @Column
    @Nullable
    private int age;

    @Column
    private boolean status = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.UNKNOWN;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Event> events;

    @Override
    public String toString(){
        return login;
    }

    @Override
    public boolean equals(Object o) {
        User other = (User)o;
        return other.login.equals(this.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.login, this.password);
    }

    public static enum Sex{
        MALE,
        FEMALE,
        UNKNOWN
    }
}
