package com.microservice.chat.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content", length=50, nullable=false)
    private String messageContent;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    public Message() {
    }

    public Message(Long messageId, String content, Date createdAt, Room room, User user) {
        this.id = messageId;
        this.messageContent = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String content) {
        this.messageContent = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }
}