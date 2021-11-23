package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.otp.GetOtpResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface OTPService {
    GetOtpResponse createOTP(BaseRequestData otpRequest) throws ApplicationException;
    GetOtpResponse checkOTP(BaseRequestData otpRequest) throws ApplicationException;


}
