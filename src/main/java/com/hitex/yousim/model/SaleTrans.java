package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Chidq
 * @project yousim
 * @created 02/04/2021 - 4:21 PM
 */
@Entity
@Data
@Table(name = "sale_trans")
public class SaleTrans extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private int transId;

    @Column(name = "product_type")
    private int productType;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status_sale_trans")
    private int statusSaleTrans;

    @Column(name = "create_source")
    private int createSource;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "voucher_code")
    private String voucherCode;

    @Column(name = "amount")
    private double amount;

}
