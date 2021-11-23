package com.hitex.yousim.dto.request.contactShopMall;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.dto.request.orderProduct.OrderRequest;
import com.hitex.yousim.model.Customer;
import lombok.Data;

import java.util.List;

@Data
public class ContactShopMallRequest extends Customer implements IRequestData {
    private double totalPrice;
    private int sourceChannelSaleId;
    private String addressDelivery;
    private String phoneDelivery;
    private String nameDelivery;
    private String cityIdDelivery;
    private String districtIdDelivery;
    private String villageIdDelivery;
    private int payMethodId;
    private String createDate;
    private int type;
    private String codeDelivery;
    private int statusPay;
    List<OrderRequest> orderRequestList;
    @Override
    public boolean isValid() {
        return false;
    }
}
