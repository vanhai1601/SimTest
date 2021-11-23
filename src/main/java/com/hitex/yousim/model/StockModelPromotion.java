package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "stock_model_promotion")
@Data
public class StockModelPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stock_model_id")
    private int stockModelId;

    @Column(name = "promotion")
    private double promotion;

    @Column(name = "type")
    private int type;
}
