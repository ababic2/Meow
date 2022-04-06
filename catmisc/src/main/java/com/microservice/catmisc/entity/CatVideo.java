package com.microservice.catmisc.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="catvide")
public class CatVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="url", nullable=false)
    private String url;

    public CatVideo(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public CatVideo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
