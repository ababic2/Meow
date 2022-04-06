package com.microservice.catmisc.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="vet")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    @Size(min = 3, max = 12, message = "name needs to have more than 3 and less than 12 letters")
    private String name;

    @Column(name="geoLocation", nullable=false)
    @Size(min = 5, max = 100, message = "geoLocation needs to have more than 3 and less than 100 letters")
    private String geoLocation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public Vet(Long id, String name, String geoLocation) {
        this.id = id;
        this.name = name;
        this.geoLocation = geoLocation;
    }

    public Vet() {
    }
}
