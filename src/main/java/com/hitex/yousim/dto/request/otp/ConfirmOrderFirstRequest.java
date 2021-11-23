package com.hitex.yousim.dto.request.otp;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.dto.request.orderProduct.ListOrderProductRequest;
import lombok.Data;

@Data
public class ConfirmOrderFirstRequest extends ListOrderProductRequest implements IRequestData {
    private String otp;
    private String action;
    @Override
    public boolean isValid() {
        return false;
    }
}
