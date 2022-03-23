package com.microservice.donation.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="donated_sum", nullable=false)
    private Float donated_sum;

    @OneToMany( mappedBy = "user")
    private List<Donation> donations = new ArrayList<>();

    public User() {
    }

    public User(Long id, Float donated_sum) {
        this.id = id;
        this.donated_sum = donated_sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDonated_sum() {
        return donated_sum;
    }

    public void setDonated_sum(Float donated_sum) {
        this.donated_sum = donated_sum;
    }
}
