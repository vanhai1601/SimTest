package com.hitex.yousim.dto.response.payment;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

/**
 * @author Chidq
 * @project yousim
 * @created 16/05/2021 - 1:50 PM
 */
@Data
public class IpnUrlResponse implements IResponseData {
    public String RspCode;
    public String Message;
}
