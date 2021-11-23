package com.hitex.yousim.dto.response.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

/**
 * @author Chidq
 * @project yousim
 * @created 16/05/2021 - 1:46 PM
 */
@Data
public class CheckOutResponse implements IResponseData {
    private String orderCode;
    private String checkoutUrl;
    private String partnerOrderCode;
    @JsonIgnore
    private String errorCode;
    @JsonIgnore
    private String errorDesc;
    @JsonIgnore
    private String partnerResJson;

    @JsonIgnore
    private int errorCodeResponse = 0;
}
