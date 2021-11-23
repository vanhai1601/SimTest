package com.hitex.yousim.dto.response.stockModel;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListStockModelRespone implements IResponseData {
    List<StockModelRespone> stockModelResponeList;
}
