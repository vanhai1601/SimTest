package com.hitex.yousim.dto.response.ListOrder;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListOrderRespone implements IResponseData {
    List<OrderRespone> listOrederRespone;
}
