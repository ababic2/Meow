package com.microservice.cat.entity;

import javax.persistence.*;

@Entity
@Table(name="description")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="gender", nullable=false)
    private String gender;

    @Column(name="age", nullable=false)
    private Integer age;

    @Column(name="species", nullable=false)
    private String species;

    public Description(Long id, String name, String gender, Integer age, String species) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.species = species;
    }

    public Description() {
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String sex) {
        this.gender = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @OneToOne(mappedBy = "description")
    private Cat cat;

    public Description(Long id, Cat cat) {
        this.id = id;
        this.cat = cat;
    }

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "chip_id", referencedColumnName = "id")
    private Chip chip;

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }
}
