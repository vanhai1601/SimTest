package com.hitex.yousim.dto.request.orderProduct;


import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class updateStatisticalVoucherRequest implements IRequestData {
    private String orderCode;
    private String isdn;
    private String voucherOld;
    private String voucherNew;
    private String namCus;
    private String email;
    private String phone;

    @Override
    public boolean isValid() {
        return false;
    }
}
