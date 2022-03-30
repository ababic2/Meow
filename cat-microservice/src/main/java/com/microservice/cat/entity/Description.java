package com.microservice.cat.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="description")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    @Size(min = 3, max = 12, message = "name needs to have more than 3 and less than 12 letters")
    private String name;

    @Column(name="gender", nullable=false)
    @Size(min = 2, max = 12, message = "gender needs to have more than 2 and less than 12 letters")
    private String gender;

    @Column(name="age", nullable=false)
    @Min(value = 0, message = "Age can not be less than 0")
    @Max(value = 30, message = "Age can not be more than 30s" ) // If someone gets mad that the max age is 30 they are a lucky person
    private Integer age;

    @Column(name="species", nullable=false)
    @Size(min = 3, max = 15, message = "species needs to have more than 2 and less than 15 letters")
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
