package com.hitex.yousim.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Immutable
@Entity
@Data
@Table( name = "v_sim_package")
public class ViewSimPackage {
    @Id
    @Column(name = "package_id")
    private int packageId;
    @Column(name = "package_code")
    private String packageCode;
    @Column(name = "package_name")
    private String packageName;
    @Column(name = "status")
    private int status;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "sms_internal")
    private int smsInternal;
    @Column(name = "sms_external")
    private int smsExternal;
    @Column(name = "voice_internal")
    private int voiceInternal;
    @Column(name = "voice_external")
    private int voiceExternal;
    @Column(name = "package_data")
    private int packageData;
    @Column(name = "function")
    private String function;
    @Column(name = "product_cycle")
    private int productCycle;
    @Column(name = "package_type")
    private int packageType;
    @Column(name = "first_month_price")
    private int firstMonthPrice;
    @Column(name = "mobile_carrier_id")
    private int mobileCarrierId;
    @Column(name = "mobile_carrier_name")
    private String mobileCarrierName;
    @Column(name = "mobile_carrier_logo")
    private String mobileCarrierLogo;
    @Column(name = "nation_id")
    private int nationId;
    @Column(name = "nation_name")
    private String nationName;
    @Column(name = "code_language")
    private String codeLanguage;

    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Column(name = "promotion")
    private Double promotion;
}
