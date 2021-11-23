package com.hitex.yousim.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "isdn")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Isdn extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isdn_id")
    private int isdnId;

    @Column(name = "isdn")
    private String isdn;

    @Column(name = "status")
    private int status;

    @Column(name = "create_source")
    private String createSource;

    @Column(name = "stock_model_id")
    private int stockModelId;

    @Column(name = "package_id_default")
    private int packageIdDefault;

    @Column(name = "package_id_list")
    private String packageIdList;

    @Column(name = "has_esim")
    private int hasEsim;

    @Column(name = "count_isdn")
    private int countIsdn;

    @Column(name = "mobile_carrier_id")
    private int mobileCarrierId;
}
