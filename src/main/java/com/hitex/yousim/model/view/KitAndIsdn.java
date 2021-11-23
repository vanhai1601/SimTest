package com.hitex.yousim.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Immutable
@Entity
@Data
@Table( name = "v_kit_and_isdn")
public class KitAndIsdn {
    @Id
    @Column(name = "isdn")
    private String isdn;

    @Column(name = "serial")
    private String serial;

    @Column(name  = "status")
    private int status;

    @Column(name = "create_source")
    private String createSource;

    @Column(name = "status_sub")
    private int statusSub;

    @Column(name = "package_id_default")
    private int packageIdDefault;

    @Column(name = "package_id_list")
    private String packageIdList;

    @Column(name = "kit_physic_type")
    private  int kitPhysicType;

    @Column(name = "count_isdn")
    private  int countIsdn;

    @Column(name = "stock_model_id")
    private  int stockModelId;

    @Column(name = "mobile_carrier_id")
    private  int mobileCarrierId;

    @Column(name = "stock_model_name")
    private String stockModelName;

    @Column(name = "price_amount")
    private double priceAmount;

    @Column(name = "product_type")
    private  int productType;

    @Column(name = "mobile_carrier_name")
    private String mobileCarrierName;

    @Column(name = "nation_name")
    private String nationName;

    @Column(name = "nation_id")
    private int nationId;

    @Column(name = "promotion")
    private  Double promotion;

    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
