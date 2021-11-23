package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.otp.GetOtpRequest;
import com.hitex.yousim.dto.response.otp.GetOtpResponse;
import com.hitex.yousim.model.Otp;
import com.hitex.yousim.repository.OTPRepository;
import com.hitex.yousim.service.OTPService;
import com.hitex.yousim.utils.GenCodeUtils;
import com.hitex.yousim.utils.PasswordEncryption;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Log4j2
public class OTPServiceImpl implements OTPService {
    @Autowired
    OTPRepository otpRepository;

    @Override
    public GetOtpResponse createOTP(BaseRequestData otpRequest) throws ApplicationException {
        GetOtpResponse getOtpResponse = new GetOtpResponse();
        try {
            Otp otp = new Otp();
            otp.setOtpCode(GenCodeUtils.oneTimePass());
            otp.setCreatedDate(LocalDateTime.now());
            otp.setExpiredDate(LocalDateTime.now().plusMinutes(3));
            otp.setStatus(Constant.OTP_ACTIVE);
            otpRepository.save(otp);
            BeanUtils.copyProperties(otp, getOtpResponse);
        } catch (Exception e) {
            throw e;
        }

        return getOtpResponse;
    }

    @Override
    public GetOtpResponse checkOTP(BaseRequestData otpRequest) throws ApplicationException {
        GetOtpResponse getOtpResponse = new GetOtpResponse();
        GetOtpRequest getOtpRequest = (GetOtpRequest) otpRequest.getWsRequest();

        try {
            String otpCode = getOtpRequest.getOtpCode();
            Otp otp = otpRepository.findOTPByOTPCode(getOtpRequest.getOtpCode());
            if (otp != null) {
                if (otpCode.equals(otp.getOtpCode())) {
                    boolean checkTime = LocalDateTime.now().isBefore(otp.getExpiredDate());
                    if (!checkTime) {
                        otp.setStatus(Constant.OTP_INACTIVE);
                        otpRepository.save(otp);
                        BeanUtils.copyProperties(otp, getOtpResponse);
                    }
                    if (otp.getStatus() == Constant.OTP_INACTIVE) {
                        throw new ApplicationException("ERR_0000103");
                    }
                } else {
                    throw new ApplicationException("ERR_0000102");
                }
            } else {
                throw new ApplicationException("ERR_0000102");
            }
        } catch (ApplicationException e) {
            throw e;
        }
        return getOtpResponse;
    }
}
