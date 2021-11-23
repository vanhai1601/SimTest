package com.hitex.yousim.dto.response.orderProduct;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;


@Data
public class ListUpdateOrderResponse  implements IResponseData {
    List<OrderProductResponse> orderProductResponseList;
}
