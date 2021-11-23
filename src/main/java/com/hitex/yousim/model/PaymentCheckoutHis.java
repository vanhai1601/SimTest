package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_checkout_his")
@Data
public class PaymentCheckoutHis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "trans_status")
    private String transStatus;

    @Column(name = "transaction_no")
    private String transactionNo;

//    @OneToOne
//    @JoinColumn(name = "trans_status", insertable = false, updatable = false)
//    private TransCode transCode;

    public PaymentCheckoutHis() {
    }

    public PaymentCheckoutHis(PaymentCheckout checkout) {
        orderCode = checkout.getOrderCode();
        transStatus = checkout.getTransStatus();
        createdAt = LocalDateTime.now();
    }
}
