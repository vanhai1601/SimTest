package com.hitex.yousim.dto.request.orderProduct;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class OrderHistoryRequest implements IRequestData {
    private int page;
    private int pageSize;
    private String phone;
    @Override
    public boolean isValid() {
        return false;
    }
}
