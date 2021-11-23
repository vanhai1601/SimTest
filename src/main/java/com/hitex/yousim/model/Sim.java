package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Chidq
 * @project yousim
 * @created 22/04/2021 - 3:48 PM
 */
@Entity
@Table(name = "sim")
@Data
public class Sim extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sim_id")
    private int simId;
    @Column(name = "serial")
    private String serial;
    @Column(name = "status")
    private int status;

    @Column(name = "create_source")
    private String createSource;
    @Column(name = "status_sub")
    private int statusSub;
    @Column(name = "stock_model_id")
    private int stockModelId;
    @Column(name = "sim_physic_type")
    private int physicType;
    @Column(name  = "mobile_carrier_id")
    private  int mobileCarrierId;
}
