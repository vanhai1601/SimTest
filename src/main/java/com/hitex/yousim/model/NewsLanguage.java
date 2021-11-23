package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "news_language")
public class NewsLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "news_id")
    private Integer newsId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "language")
    private String language;
}
