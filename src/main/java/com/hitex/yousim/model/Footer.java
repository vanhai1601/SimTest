package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "footer")
public class Footer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "footer_id")
    private int footerId;
    @Column(name = "hotline")
    private String hotline;
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private boolean status;

}
