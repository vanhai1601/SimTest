package com.hitex.yousim.dto.request.otp;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Otp;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class GetOtpRequest extends Otp implements IRequestData {
    private String otpCode;
    private String isdn;
    private String action;


    @Override
    public boolean isValid(){
        return StringUtils.hasText(isdn) && StringUtils.hasText(action);
    }
}
