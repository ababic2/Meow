package com.microservice.cat.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="health")
public class Health {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Health(Long id) {
        this.id = id;
    }

    public Health() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "health")
    private Cat cat;

    @OneToMany( mappedBy = "health")
    private List<Vaccination> vaccinations= new ArrayList<>();

    @OneToMany( mappedBy = "health")
    private List<Illnesses> illnesses= new ArrayList<>();

    public Health(Long id, Cat cat, List<Vaccination> vaccinations, List<Illnesses> illnesses) {
        this.id = id;
        this.cat = cat;
        this.vaccinations = vaccinations;
        this.illnesses = illnesses;
    }
}
