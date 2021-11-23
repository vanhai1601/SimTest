package com.hitex.yousim.dto.request.payment;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

/**
 * @author Chidq
 * @project yousim
 * @created 16/05/2021 - 1:49 PM
 */
@Data
public class IpnUrlRequest implements IRequestData {
    private String vnp_TmnCode;
    private String vnp_Amount;
    private String vnp_BankCode;
    private String vnp_BankTranNo;
    private String vnp_CardType;
    private String vnp_PayDate;
    private String vnp_CurrCode;
    private String vnp_OrderInfo;
    private String vnp_TransactionNo;
    private String vnp_ResponseCode;
    private String vnp_TxnRef;
    private String vnp_SecureHashType;
    private String vnp_SecureHash;

    @Override
    public boolean isValid() {
        return true;
    }
}
