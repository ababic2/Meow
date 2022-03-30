package com.microservice.login.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable=false)
    @Size(min=5, max=15, message = "username must be between 5 and 15 letters")
    private String username;

    @Column(name="email", nullable=false)
    @Size(min=5, max=15, message = "email must be between 5 and 15 symbols")
    private String email;

    @Column(name="password", nullable=false)
    @Size(min=5, message = "password must have more than 5 symbols")
    private String password;

    @Column(name="cat", nullable = false)
    private boolean cat;


    public User(String username, String email, String password, boolean cat) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.cat = cat;
    }

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCat() {
        return cat;
    }

    public void setCat(boolean cat) {
        this.cat = cat;
    }

}
