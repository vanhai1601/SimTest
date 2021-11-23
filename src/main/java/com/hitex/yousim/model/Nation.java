package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "nation")
public class Nation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nation")
    private int idNation;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "ensign")
    private String ensign;
}
