package com.hitex.yousim.dto.request.orderProduct;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.OrderProduct;
import lombok.Data;

@Data
public class OrderRequest extends OrderProduct implements IRequestData {
    private int transId;
    private int quantity;
//    loại sản phẩm 1: kit 2: isdn
    private int productType;
    // 1 e sim, 2 u sim
    private int physicType;
    private int stockModelId;
    private int packageId;
    private String isdn;
//    mã voucher khuyến mãi
    private String voucherCode;
    private String price;
    private String serial;
    private String packageName;
    private int detailId;
    private int packageIdDefault;
    private double amount;

    @Override
    public boolean isValid() {
        return false;
    }
}
