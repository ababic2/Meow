package com.microservice.cat.entity;

import javax.persistence.*;

@Entity
@Table(name="chip")
public class Chip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Chip(Long id) {
        this.id = id;
    }

    public Chip() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "chip")
    private Description description;
}
