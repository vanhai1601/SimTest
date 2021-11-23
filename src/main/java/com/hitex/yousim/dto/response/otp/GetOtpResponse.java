package com.hitex.yousim.dto.response.otp;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.Otp;
import lombok.Data;

@Data
public class GetOtpResponse extends Otp implements IResponseData {
    private String otpCode;
    private String isdn;
    private String action;
}
