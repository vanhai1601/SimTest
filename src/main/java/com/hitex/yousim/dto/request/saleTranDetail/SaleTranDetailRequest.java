package com.hitex.yousim.dto.request.saleTranDetail;


import lombok.Data;

@Data
public class SaleTranDetailRequest {
    private int transId;
    private String isdn;
    private String serial;
    private String packageName;
    private String code;
    private int productType;
    private int packageIdDefault;
    private double amount;
    private int packageId;
    private int quantity;
    private  int stockModelId;
    private int physicType;
}
