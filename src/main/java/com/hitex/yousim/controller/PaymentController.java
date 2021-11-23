package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.payment.CheckOutRequest;
import com.hitex.yousim.dto.request.payment.IpnUrlRequest;
import com.hitex.yousim.dto.response.payment.CheckOutResponse;
import com.hitex.yousim.dto.response.payment.IpnUrlResponse;
import com.hitex.yousim.dto.response.payment.ReturnUrlResponse;
import com.hitex.yousim.service.PaymentService;
import com.hitex.yousim.utils.PaymentUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class PaymentController extends BaseController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/getUrlVnPay")
    public ResponseEntity checkOutVnPay(@RequestBody BaseRequestData<CheckOutRequest> baseRequestData, HttpServletRequest httpServletRequest) throws ApplicationException {
        try {
            CheckOutRequest request = baseRequestData.getWsRequest();
            request.setIpAddress(PaymentUtils.getIpAddress(httpServletRequest));
            CheckOutResponse checkOutResponse = paymentService.getPaymentVnPay(request);
            return success(checkOutResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/ipnUrlVnPay")
    public ResponseEntity ipnUrlVnPay(@RequestBody BaseRequestData<IpnUrlRequest> baseRequestData) throws ApplicationException{
        try {
            IpnUrlRequest request = baseRequestData.getWsRequest();
            IpnUrlResponse ipnUrlResponse = paymentService.ipnUrlVnPay(request);
            return success(ipnUrlResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/returnUrlVnPay")
    public ResponseEntity returnUrlVnPay(@RequestBody BaseRequestData<IpnUrlRequest> baseRequestData) throws ApplicationException {
        try {
            IpnUrlRequest request = baseRequestData.getWsRequest();
            ReturnUrlResponse returnUrlResponse = paymentService.returnUrlVnPay(request);
            return success(returnUrlResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
