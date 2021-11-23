package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "service_page")
@Data
public class ServicePage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="service_id")
    private int serviceId;
    @Column(name = "icon")
    private String icon;
    @Column(name = "image")
    private String image;
    @Column(name = "position")
    private int position;
    @Column(name = "type")
    private int type;
    @Column(name = "status")
    private boolean status;
}
