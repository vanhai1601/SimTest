package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.payment.CheckOutRequest;
import com.hitex.yousim.dto.request.payment.IpnUrlRequest;
import com.hitex.yousim.dto.response.payment.CheckOutResponse;
import com.hitex.yousim.dto.response.payment.IpnUrlResponse;
import com.hitex.yousim.dto.response.payment.ReturnUrlResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface PaymentService {
    CheckOutResponse getPaymentVnPay(CheckOutRequest checkOutRequest) throws ApplicationException;
    IpnUrlResponse ipnUrlVnPay (IpnUrlRequest request) throws ApplicationException;
    ReturnUrlResponse returnUrlVnPay(IpnUrlRequest request) throws ApplicationException;
}