package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.otp.GetOtpRequest;
import com.hitex.yousim.dto.response.otp.GetOtpResponse;
import com.hitex.yousim.service.OTPService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/")
public class OTPController extends BaseController{
    @Autowired
    OTPService otpService;

    @PostMapping(value = "checkOTP")
    @ResponseBody
    public ResponseEntity checkOTP(@RequestBody BaseRequestData<GetOtpRequest> requestData) throws ApplicationException {
        try {
            GetOtpResponse getOtpResponse = otpService.checkOTP(requestData);
            return success(getOtpResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
