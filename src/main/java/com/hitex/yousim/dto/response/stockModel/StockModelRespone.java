package com.hitex.yousim.dto.response.stockModel;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.StockModel;
import lombok.Data;

@Data
public class StockModelRespone extends StockModel implements IResponseData {
    private double price;
}
