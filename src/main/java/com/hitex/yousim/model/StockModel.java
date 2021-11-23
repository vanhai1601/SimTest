package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Chidq
 * @project yousim
 * @created 03/04/2021 - 10:24 AM
 */
@Entity
@Data
@Table(name = "stock_model")
public class StockModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_model_id")
    private int stockModelId;
    @Column(name = "stock_model_name")
    private String stockModelName;
    @Column(name = "stock_model_code")
    private String stockModelCode;
    @Column(name = "type")
    private int type;
}
