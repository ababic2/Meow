package com.microservice.chat.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    @Size(max = 50, message = "room name too long")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "users_rooms",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> enrolledUsers = new HashSet<>();

    @OneToMany(mappedBy = "room")
    private List<Message> messages;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void setEnrolledUsers(Set<User> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}