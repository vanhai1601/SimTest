package com.hitex.yousim.dto.request.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hitex.yousim.dto.request.IRequestData;
//import com.hitex.yousim.utils.Validators;
import lombok.Data;

/**
 * @author Chidq
 * @project yousim
 * @created 16/05/2021 - 1:47 PM
 */
@Data
public class CheckOutRequest implements IRequestData {
    private String methodCode;
    private String optionCode;
    private String bankCode;
    private String ipAddress;

    private String orderCode;
    @JsonIgnore
    private Double totalAmount;

    private String orderDescription;

    private String curCode;

    private int type = 0;
    private String callback;

    private int isTest;

    @JsonIgnore
    private String partnerReqJson;
    private String urlReturn;
    private String urlCancel;

    @Override
    public boolean isValid() {
        return false;
    }

//    @Override
//    @JsonIgnore
//    public boolean isValid()
//    return !Validators.hasStringEmpty(orderCode);
}
