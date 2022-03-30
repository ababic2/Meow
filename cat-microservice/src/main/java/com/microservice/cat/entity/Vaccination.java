package com.microservice.cat.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name="vaccination")
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date", nullable=false)
    private Date date;

    @Column(name="vaccine", nullable=false)
    @Size(min = 5, max = 20)
    private String vaccine;

    public Vaccination(Long id, Date date, String vaccine) {
        this.id = id;
        this.date = date;
        this.vaccine = vaccine;
    }

    public Vaccination() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "health_id", referencedColumnName = "id")
    private Health health;

}
