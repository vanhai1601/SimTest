package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Chidq
 * @project yousim
 * @created 16/05/2021 - 3:14 PM
 */
@Data
@Entity
@Table(name = "return_vn_pay_log")
public class ReturnVnPayLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String request;

    private String response;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "partner_trans_desc")
    private String partnerTransStatus;

    @Column(name = "order_code")
    private String orderCode;
}