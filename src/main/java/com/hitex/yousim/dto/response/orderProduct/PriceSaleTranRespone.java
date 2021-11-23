package com.hitex.yousim.dto.response.orderProduct;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

@Data
public class PriceSaleTranRespone implements IResponseData {
    private double priceSimPackage;
    private String packageName;
    private String isdn;
    private double priceStockModel;
    private String stockModelName;
    private double discountMoney;
    private String voucherCode;
    private double priceBeforeDiscount;
    private double priceSimPhysicType;
}
