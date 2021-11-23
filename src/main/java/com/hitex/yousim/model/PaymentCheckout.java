package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "payment_checkout")
public class PaymentCheckout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method_code")
    private String methodCode;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "partner_order_code")
    private String partnerOrderCode;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "paid_at")
    private String paidAt;

    @Column(name = "trans_status")
    private String transStatus;

//    @OneToOne
//    @JoinColumn(name = "trans_status", insertable = false, updatable = false)
//    private TransCode transCode;

    @Column(name = "is_test")
    private Integer isTest;

    @Column(name = "is_used")
    private int isUsed;

    @Column(name = "checkout_url")
    private String checkoutUrl;

    @Column(name = "total")
    private Integer totalAmount;


    @Column(name = "type")
    private Integer type = 0;

    public PaymentCheckout(PaymentCheckoutHis checkoutHis) {
        orderCode = checkoutHis.getOrderCode();
        transStatus = checkoutHis.getTransStatus();
    }
    public PaymentCheckout() {
    }
}
