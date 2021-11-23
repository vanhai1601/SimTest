package com.hitex.yousim.dto.request.payment;

import lombok.Data;

/**
 * @author Chidq
 * @project yousim
 * @created 16/05/2021 - 1:57 PM
 */
@Data
public class VnPayRequest {
    private String vnpOrderInfo;
    private String orderType;
    private double amount;
    private String language;
    private String bankCode;
    private String version;
    private String ipAddress;
    private String returnUrl;
    private String tmnCode;
    private int isTest;
    private String payUrl;
    private String queryUrl;
    private String hashKey;
    private String orderCode;
    private String createDate;

}
