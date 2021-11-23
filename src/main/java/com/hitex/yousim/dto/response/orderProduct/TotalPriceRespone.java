package com.hitex.yousim.dto.response.orderProduct;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;


@Data
public class TotalPriceRespone implements IResponseData {
    long totalPrice;
    //doanh thu
    long revenue;
    // số lượng đơn hàng
    int totalItem;
    int priceShip;
    List<PriceSaleTranRespone> priceSaleTranResponeList;
}
