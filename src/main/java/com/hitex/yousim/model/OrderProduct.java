package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Chidq
 * @project yousim
 * @created 30/03/2021 - 3:44 PM
 */
@Entity
@Table(name = "order_product")
@Data
public class OrderProduct extends BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "order_code")
    private String orderCode;
    @Column(name = "amount")
    private double amount;
    @Column(name = "status_sale")
    private int statusSale;
    @Column(name = "status_pay")
    private int statusPay;

    @Column(name = "create_source")
    private String createSource;
    @Column(name = "cust_id")
    private int custId;
    @Column(name = "partner_id")
    private int partnerId;
    @Column(name = "pay_method_id")
    private int payMethodId;
    @Column(name = "city_id")
    private String cityId;
    @Column(name = "district_id")
    private String districtId;
    @Column(name = "village_id")
    private String villageId;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "type")
    private int type;
    @Column(name = "phone_delivery")
    private String phoneDelivery;
    @Column(name = "name_delivery")
    private String nameDelivery;
    @Column(name = "ghtk_id")
    private String ghtkId;
//    @Column(name = "code_shipment")
//    private String codeShipment;
    @Column(name = "order_code_ghtk")
    private String orderCodeGHTK;
    @Column(name = "status_ghtk")
    private String statusGHTK;
    @Column(name ="id_source_channel_sale")
    private int sourceChannelSaleId;
    @Column(name ="create_date")
    private String createDate;
}
