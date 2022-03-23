package com.microservice.cat.entity;

import javax.persistence.*;

@Entity
@Table(name="cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Cat(Long id) {
        this.id = id;
    }

    public Cat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "description_id", referencedColumnName = "id")
    private Description description;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "health_id", referencedColumnName = "id")
    private Health health;


    public Cat(Long id, Account account, Description description, Health health) {
        this.id = id;
        this.account = account;
        this.description = description;
        this.health = health;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }
}
