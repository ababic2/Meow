package com.microservice.donation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "Donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donation_id;

    @Column(name="amount", nullable=false)
    @Min(value = 0, message = "donation amount can't be negative")
    private Float amount;

    @Column(name="date", nullable=false)
    private Date date;

    @Column(name="type", nullable=false)
    private String type;


    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Donation() {
    }

    public Donation(Float amount, Date date, String type, User user) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(Long donation_id) {
        this.donation_id = donation_id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
