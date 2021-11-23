package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sim_package_language")
public class SimpackageLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_language_id")
    private int simPackageLanguage;
    @Column(name = "package_id")
    private int package_id;
    @Column(name = "price")
    private double price;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "function")
    private String function;
    @Column(name = "description")
    private String description;
    @Column(name = "package_name")
    private String packageName;
    @Column(name = "code_language")
    private String codeLanguage;
}
