package com.microservice.catmisc.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="article")
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false)
    @Size(min = 3, max = 50, message = "title needs to have more than 3 and less than 50 letters")
    private String title;

    @Column(name="catBreed")
    @Size(min = 2, max = 20, message = "catBreed needs to have more than 2 and less than 20 letters")
    private String catBreed;

    @Column(name="content", nullable=false)
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatBreed() {
        return catBreed;
    }

    public void setCatBreed(String catBreed) {
        this.catBreed = catBreed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article() {
    }

    public Article(Long id, String title, String catBreed, String content, Wikipawdia wikipawdia) {
        this.id = id;
        this.title = title;
        this.catBreed = catBreed;
        this.content = content;
        this.wikipawdia = wikipawdia;
    }

    public Wikipawdia getWikipawdia() {
        return wikipawdia;
    }

    public void setWikipawdia(Wikipawdia wikipawdia) {
        this.wikipawdia = wikipawdia;
    }



    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "wikipawdia_id", referencedColumnName = "id")
    private Wikipawdia wikipawdia;


}
