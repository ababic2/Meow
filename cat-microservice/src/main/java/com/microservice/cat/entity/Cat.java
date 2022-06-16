package com.microservice.cat.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @Column(name="description", nullable=false)
    @Size(min=5, max=30, message = "description must be between 5 and 30 letters")
    private String description;

    @Column(name="name", nullable=false)
    @Size(min=5, max=10, message = "name must be between 5 and 10 letters")
    private String name;

    @Column(name="species", nullable=false)
    @Size(min=5, max=10, message = "species must be between 5 and 10 letters")
    private String species;

    @Column(name="contact", nullable=false)
    @Size(min=5, max=25, message = "contact must be between 5 and 25 letters")
    private String contact;

    @Column(name="chip", nullable = false)
    private boolean chip;

    @Column(name="vaccinated", nullable = false)
    private boolean vaccinated;

    @Column(name="healthy", nullable = false)
    private boolean healthy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

//    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "description_id", referencedColumnName = "id")
//    private Description description;
//
//    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "health_id", referencedColumnName = "id")
//    private Health health;


//    public Cat(Long id, Account account, Description description, Health health) {
//        this.id = id;
//        this.account = account;
//        this.description = description;
//        this.health = health;
//    }


//    public Cat(Long id, String description, boolean chip, boolean vaccinated, boolean healthy, Account account) {
//        this.id = id;
//        this.description = description;
//        this.chip = chip;
//        this.vaccinated = vaccinated;
//        this.healthy = healthy;
//        this.account = account;
//    }


    public Cat(Long id, String description, String name, String species, String contact, boolean chip, boolean vaccinated, boolean healthy, Account account) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.species = species;
        this.contact = contact;
        this.chip = chip;
        this.vaccinated = vaccinated;
        this.healthy = healthy;
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChip() {
        return chip;
    }

    public void setChip(boolean chip) {
        this.chip = chip;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    //    public Description getDescription() {
//        return description;
//    }
//
//    public void setDescription(Description description) {
//        this.description = description;
//    }
//
//    public Health getHealth() {
//        return health;
//    }
//
//    public void setHealth(Health health) {
//        this.health = health;
//    }
}
