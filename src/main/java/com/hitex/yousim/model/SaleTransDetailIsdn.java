package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Chidq
 * @project yousim
 * @created 03/04/2021 - 2:52 PM
 */
@Entity
@Data
@Table(name = "sale_trans_detail_isdn")
public class SaleTransDetailIsdn extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "status_sale_trans_isdn")
    private int statusSaleTransIsdn;
    @Column(name = "create_source")
    private int createSource;
    @Column(name = "isdn_id")
    private int isdnId;
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "sim_physic_type")
    private int physicType;
    @Column(name = "package_id")
    private int packageId;

    @Column(name = "serial")
    private String serial;
    @Column(name = "stock_model_id")
    private int stockModelId;
}
