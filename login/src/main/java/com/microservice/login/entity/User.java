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
    private String userName;

    @Column(name="email", nullable=false)
    @Size(min=5, max=15, message = "email must be between 5 and 15 symbols")
    private String email;

    @Column(name="password", nullable=false)
    @Size(min=5, message = "password must have more than 5 symbols")
    private String password;

    @Column(name="cat", nullable = false)
    private boolean cat;

    @Column(name = "role")
    private int role;


    public User(Long id, String userName, String email, String password, boolean cat, int role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.cat = cat;
        this.role = role;
    }

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
