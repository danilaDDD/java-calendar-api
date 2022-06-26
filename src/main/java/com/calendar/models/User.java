package com.calendar.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Accessors(chain = true)
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;

    @Column
    @Setter @Getter private String login;

    @Column(columnDefinition = "TEXT")
    @Setter @Getter private String password;

    @Column
    @Nullable
    @Setter @Getter private String fio;

    @Column
    @Nullable
    @Setter @Getter private String email;

    @Column
    @Nullable
    @Setter @Getter private int age;

    @Column
    @Setter @Getter private boolean status = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter @Getter private Sex sex = Sex.UNKNOWN;

    @Column(nullable = false)
    @Getter @Setter private Role role = Role.USER;

    @Column(nullable = false, name = "admin")
    private boolean admin = false;

    public boolean isAdmin(){
        return admin;
    }

    public void isAdmin(boolean admin){
        this.admin = admin;
    }

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

    public static enum Role{
        ADMIN,
        USER
    }
}
