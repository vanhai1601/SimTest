package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "stock_model_price")
@Data
public class StockModelPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_model_price_id")
    private int stockModelPriceId;
    @Column(name = "stock_model_id")
    private int stockModelId;

    @Column(name = "sale_type_id")
    private  int saleTypeId;

    @Column(name  = "price_amount")
    private double priceAmount;
}
