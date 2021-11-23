package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "service_page_language")
public class ServicePageLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int languageId;
    @Column(name = "service_id")
    private int serviceId;
    @Column(name = "title")
    private String title;
    @Column(name = "descriptions")
    private String descriptions;
    @Column(name = "code_language")
    private String codeLanguage;
}
