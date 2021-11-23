package com.hitex.yousim.dto.response.SaleTranDetail;

import lombok.Data;

/**
 * @author Chidq
 * @project yousim
 * @created 03/04/2021 - 4:08 PM
 */
@Data
public class SaleTransDetailResponse {
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
