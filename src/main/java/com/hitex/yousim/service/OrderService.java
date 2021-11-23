package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.orderProduct.OrderProductResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface OrderService {
    OrderProductResponse addOrder(BaseRequestData baseRequestData) throws ApplicationException;
}
