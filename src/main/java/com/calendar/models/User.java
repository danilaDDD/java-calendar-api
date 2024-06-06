package com.calendar.models;

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
@NoArgsConstructor
public class User implements AuthEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;

    @Column(unique = true)
    private String login;

    @Column(columnDefinition = "TEXT")
    @Setter @Getter String encodedPassword;

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
        User other = (User)o;
        return other.id.equals(other.getId());
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
