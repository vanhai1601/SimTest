package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "return_ghtk_log")
public class ReturnGHTKLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String request;

    private String response;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "api_name")
    private String nameApi;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "partner_trans_desc")
    private String partnerTransStatus;
}
