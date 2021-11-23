package com.hitex.yousim.dto.response.payment;

import lombok.Data;

/**
 * @author Chidq
 * @project yousim
 * @created 16/05/2021 - 2:00 PM
 */
@Data
public class HashResponse {
    private String queryUrl;
    private String vnpSecureHash;
}