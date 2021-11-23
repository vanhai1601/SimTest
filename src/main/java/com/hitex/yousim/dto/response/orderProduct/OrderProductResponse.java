package com.hitex.yousim.dto.response.orderProduct;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.OrderProduct;
import lombok.Data;

/**
 * @author Chidq
 * @project yousim
 * @created 30/03/2021 - 4:44 PM
 */
@Data
public class OrderProductResponse extends OrderProduct implements IResponseData {
    private String cusName;
    private int sex;
    private String address;
    private String email;
    private String fb;
    private int typeDoc;
    private String phone;
    private int price;
    private String typeNameOrder;
    private String idCardFont;
    private String idCardBack;
    private String avatar;
    private String cityNameDevilery;
    private String districtNameDevilery;
    private String villageNameDevilery;
    private String sourceChannelSaleName;
}
