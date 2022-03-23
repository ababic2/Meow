package com.microservice.cat.entity;

import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Account(Long id) {
        this.id = id;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "account")
    private Cat cat;


    public Account(Long id, Cat cat) {
        this.id = id;
        this.cat = cat;
    }


}
