package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.stockModel.ListStockModelRespone;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface StockModelService {
    ListStockModelRespone getListStockModel(BaseRequestData baseRequestData) throws ApplicationException;
}
