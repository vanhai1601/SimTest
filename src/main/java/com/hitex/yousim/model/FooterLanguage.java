package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "footer_language")
public class FooterLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private int languageId;
    @Column(name = "code_language")
    private String codeLanguage;
    @Column(name = "descriptions")
    private String descriptions;
    @Column(name = "owner")
    private String owner;
    @Column(name = "business_code")
    private String businessCode;
    @Column(name = "responsible")
    private String responsible;
    @Column(name = "address")
    private String address;
    @Column(name = "footer_id")
    private int footerId;
}
