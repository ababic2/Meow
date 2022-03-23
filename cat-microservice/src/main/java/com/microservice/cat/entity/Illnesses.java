package com.microservice.cat.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="illnesses")
public class Illnesses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="illness", nullable=false)
    private String illness;

    @Column(name="therapy", nullable=true)
    private String therapy;

    @Column(name="date", nullable=false)
    private Date date;

    public Illnesses(Long id,String illness, String therapy, Date date) {
        this.id = id;
        this.illness = illness;
        this.therapy = therapy;
        this.date = date;
    }

    public Illnesses() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "health_id", referencedColumnName = "id")
    private Health health;



}
