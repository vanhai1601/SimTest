package com.hitex.yousim.dto.request.stockModel;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.StockModel;

public class StockModelRequest extends StockModel implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
