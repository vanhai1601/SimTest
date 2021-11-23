package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "otp")
public class Otp {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "isdn")
    private String isdn;

    @Column(name = "otp_code")
    private String otpCode;

    @Column(name = "action")
    private String action;

    @Column(name = "status")
    private int status;

    @Column(name = "created_date")

    private LocalDateTime createdDate;

    @Column(name = "expired_date")

    private LocalDateTime expiredDate;



}
