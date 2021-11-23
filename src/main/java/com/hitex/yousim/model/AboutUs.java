package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "about_us")
public class AboutUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "partner")
    private String partner;
    @Column(name = "image")
    private String image;
    @Column(name = "type")
    private int type;
}
