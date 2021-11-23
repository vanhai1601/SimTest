package com.hitex.yousim.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "pay_method")
public class PayMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_method_id")
    private int payMethodId;

    @Column(name = "name_pay_method")
    private String namePayMethod;

    @Column(name = "status_pay_method")
    private String statusPayMethod;
}
