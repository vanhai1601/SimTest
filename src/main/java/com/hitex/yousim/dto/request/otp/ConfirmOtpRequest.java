package com.hitex.yousim.dto.request.otp;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class ConfirmOtpRequest implements IRequestData {

    private String isdn;
    private String code;
    private String action;

    @Override
    public boolean isValid() {
        return StringUtils.hasText(isdn) && StringUtils.hasText(code) && StringUtils.hasText(action);
    }
}
