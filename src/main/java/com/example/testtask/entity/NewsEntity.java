package com.example.testtask.entity;

import javax.persistence.*;

@Entity
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;
    private String newsName;
    private String shortDescription;
    private String fullDescription;

    @OneToOne
    private NewsTypeEntity newsTypeId;

    public NewsEntity() {}

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public NewsTypeEntity getNewsTypeId() {
        return newsTypeId;
    }

    public void setNewsTypeId(NewsTypeEntity newsTypeId) {
        this.newsTypeId = newsTypeId;
    }
}
