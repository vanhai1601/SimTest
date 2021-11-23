package com.hitex.yousim.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "kit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KIT extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_id")
    private int kitId;

    @Column(name = "serial")
    private String serial;

    @Column(name = "isdn")
    private String isdn;

    @Column(name = "status")
    private int status;

    @Column(name = "create_source")
    private String createSource;

    @Column(name = "status_sub")
    private int statusSub;

    @Column(name = "stock_model_id")
    private int stockModelId;

    @Column(name = "package_id_default")
    private int packageIdDefault;

    @Column(name = "package_id_list")
    private String packageIdList;

    @Column(name = "kit_physic_type")
    private int kitPhysicType;

    @Column(name = "count_isdn")
    private int countIsdn;

    @Column(name = "mobile_carrier_id")
    private int mobileCarrierId;
}
