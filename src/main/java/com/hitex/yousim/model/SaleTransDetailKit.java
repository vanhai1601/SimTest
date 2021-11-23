package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Chidq
 * @project yousim
 * @created 03/04/2021 - 2:52 PM
 */
@Entity
@Data
@Table(name = "sale_trans_detail_kit")
public class SaleTransDetailKit extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "status_sale_trans_kit")
    private int statusSaleTransKit;
    @Column(name = "create_source")
    private int createSource;
    @Column(name = "kit_id")
    private int kitId;
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "package_id")
    private int packageId;
    @Column(name = "stock_model_id")
    private int stockModelId;
}
