package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "about_us_language")
public class AboutUsLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "title")
    private String title;
    @Column(name = "id_about_us")
    private int idAboutUs;
    @Column(name = "language")
    private String language;
}
