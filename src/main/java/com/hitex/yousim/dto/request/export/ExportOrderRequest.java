package com.hitex.yousim.dto.request.export;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.OrderProduct;
import lombok.Data;

@Data
public class ExportOrderRequest extends OrderProduct implements IRequestData {

    private String startTime;
    private String endTime;
    private int purchase;

    @Override
    public boolean isValid() {
        return false;
    }
}
