package com.microservice.catmisc.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="wikipawdia")
public class Wikipawdia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wikipawdia() {
    }

    public Wikipawdia(Long id, List<Article> articles) {
        this.id = id;
//        this.articles = articles;
    }

//    @OneToMany( mappedBy = "wikipawdia")
//    private List<Article> articles= new ArrayList<>();


}
