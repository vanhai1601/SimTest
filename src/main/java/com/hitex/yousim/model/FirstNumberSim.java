package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "first_number_sim")
public class FirstNumberSim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_number")
    private String firstNumber;
    @Column(name = "mobile_carrier_id")
    private int mobileCarrierId;
}
