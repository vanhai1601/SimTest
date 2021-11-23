package com.hitex.yousim.dto.response.payment;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReturnUrlResponse implements IResponseData {
    public String bankCode;
    public String amount;
    public LocalDateTime time;
    public String tnxRef;
    public String status;
}
