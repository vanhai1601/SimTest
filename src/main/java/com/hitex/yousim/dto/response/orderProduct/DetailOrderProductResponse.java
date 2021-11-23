package com.hitex.yousim.dto.response.orderProduct;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.dto.response.SaleTranDetail.SaleTransDetailResponse;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Chidq
 * @project yousim
 * @created 03/04/2021 - 4:01 PM
 */
@Data
public class DetailOrderProductResponse implements IResponseData {
    private int orderId;
    private String orderCode;
    private int custId;
    private int statusSale;
    private int quantity;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    private Timestamp updateTime;
    private double price;
    private String cusName;
    private int sex;
    private String email;
    private String fb;
    private int typeDoc;
    private String phone;
    // địa chỉ người đặt đơn
    private String address;
    private String cityId;
    private String districtId;
    private String villageId;
    private String cityName;
    private String districtName;
    private String villageName;
    // địa chỉ người nhận hàng
    private String deliveryAddress;
    private String deliveryCityName;
    private String deliveryDistrictName;
    private String deliveryVillageName;
    private String deliveryCityId;
    private String deliveryDistrictId;
    private String deliveryVillageId;
    // thông tin khách hàng
    private String idCardFont;
    private String idCardBack;
    private String idCard;
    private String dateIssueCard;
    private String placeIssueCard;
    private String signature;
    private String avatar;
    private String typeDocName;
    private String dateOfBirth;
    private String phoneDelivery;
    private String nameDelivery;
//    private String typeNameOrder;
    private int typeId;
    private int sourceChannelSaleId;
    private String sourceChannelSaleName;
    private int channelDelivery;
    private String ghtkCode;
    private String ecommerceCode;
    private int payMethodId;
    private int priceShip;
    private String createDate;


    List<SaleTransDetailResponse> detail;
}
