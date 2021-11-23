package com.hitex.yousim.service.impl;

import com.google.gson.Gson;
import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.payment.CheckOutRequest;
import com.hitex.yousim.dto.request.payment.IpnUrlRequest;
import com.hitex.yousim.dto.request.payment.VnPayRequest;
import com.hitex.yousim.dto.response.payment.CheckOutResponse;
import com.hitex.yousim.dto.response.payment.HashResponse;
import com.hitex.yousim.dto.response.payment.IpnUrlResponse;
import com.hitex.yousim.dto.response.payment.ReturnUrlResponse;
import com.hitex.yousim.model.*;
import com.hitex.yousim.repository.*;
import com.hitex.yousim.service.PaymentService;
import com.hitex.yousim.utils.DateUtils;
import com.hitex.yousim.utils.PaymentUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PayMentCheckoutRepository payMentCheckoutRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private PayMentMethodRepository payMentMethodRepository;
    @Autowired
    private PayMentCheckoutHisRepository payMentCheckoutHisRepository;
    @Autowired
    private ReturnVnpayLogRepository returnVnpayLogRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CheckOutResponse getPaymentVnPay(CheckOutRequest checkOutRequest) throws ApplicationException {
        try {
            String orderCode = checkOutRequest.getOrderCode();
            PaymentCheckout paymentCheckout = payMentCheckoutRepository.getByOrderCode(orderCode);
            if(!ObjectUtils.isEmpty(paymentCheckout)){
                if(paymentCheckout.getTransStatus().equalsIgnoreCase(Constant.PAYMENT.TRANS.WAITING)){
                    CheckOutResponse checkOutResponse = new CheckOutResponse();
                    BeanUtils.copyProperties(paymentCheckout, checkOutResponse);
                    return checkOutResponse;
                } else {
                    throw new ApplicationException("ERR_0000304");
                }
            }
            OrderProduct orderProduct = orderProductRepository.findByOrderCode(orderCode);
            if(ObjectUtils.isEmpty(orderProduct)){
                throw new ApplicationException("ERR_0000305");
            }
            if(ObjectUtils.isEmpty(orderProduct.getAmount()) || orderProduct.getAmount() <= 0){
                throw new ApplicationException("ERR_0000306");
            }
            checkOutRequest.setTotalAmount(orderProduct.getAmount());
            PaymentMethod paymentMethod = payMentMethodRepository.findByMedthodCode(Constant.PAYMENT.PARTNER.VNPAY);

            if(ObjectUtils.isEmpty(paymentMethod)) {
                throw new ApplicationException("ERR_0000307");
            }
            VnPayRequest vnPayRequest = new VnPayRequest();
            vnPayRequest.setVersion(paymentMethod.getVersion());
            vnPayRequest.setReturnUrl(StringUtils.isEmpty(checkOutRequest.getUrlReturn()) ? (checkOutRequest.getIsTest() == 0
                    ? paymentMethod.getReturnUrlLive() : paymentMethod.getReturnUrlTest()) : checkOutRequest.getUrlReturn());
            vnPayRequest.setTmnCode(checkOutRequest.getIsTest() == 0 ? paymentMethod.getUsername() : paymentMethod.getUsernameTest());
            vnPayRequest.setPayUrl(checkOutRequest.getIsTest() == 0 ? paymentMethod.getCheckoutUrlLive() : paymentMethod.getCheckoutUrlSandbox());
            vnPayRequest.setHashKey(checkOutRequest.getIsTest() == 0 ? paymentMethod.getRawPassword() : "FCAWMBRRSPUMHRBFDGCEDOTFDGEORZIV");
            vnPayRequest.setAmount(checkOutRequest.getTotalAmount());
            vnPayRequest.setVnpOrderInfo("Thanh toan don hang");
            vnPayRequest.setIpAddress(checkOutRequest.getIpAddress());
            vnPayRequest.setOrderType(Constant.PAYMENT.VNPAY.ORDER_TYPE.SIM);
            vnPayRequest.setOrderCode(checkOutRequest.getOrderCode());

            PaymentCheckout checkout = this.setUpRequestPay(vnPayRequest);

            checkout.setIsTest(checkOutRequest.getIsTest());
            PaymentCheckout rs = payMentCheckoutRepository.save(checkout);
            if (ObjectUtils.isEmpty(rs)) {
                throw new ApplicationException("ERR_0000310");
            }

            PaymentCheckoutHis his = new PaymentCheckoutHis(rs);
            his.setCreatedAt(LocalDateTime.now());
            his.setOrderCode(checkOutRequest.getOrderCode());
            his.setTransStatus(Constant.PAYMENT.TRANS.WAITING);
            payMentCheckoutHisRepository.save(his);

            CheckOutResponse checkOutResponse = new CheckOutResponse();
            checkOutResponse.setOrderCode(checkOutRequest.getOrderCode());
            checkOutResponse.setCheckoutUrl(checkout.getCheckoutUrl());
            return checkOutResponse;
        } catch (ApplicationException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IpnUrlResponse ipnUrlVnPay(IpnUrlRequest request) throws ApplicationException {
        IpnUrlResponse ipnUrlResponse = new IpnUrlResponse() ;
        try {
            try {
                PaymentCheckout paymentCheckout = payMentCheckoutRepository.getByOrderCode(request.getVnp_TxnRef());
                OrderProduct orderProduct = orderProductRepository.findByOrderCode(request.getVnp_TxnRef());
                if(ObjectUtils.isEmpty(paymentCheckout) || ObjectUtils.isEmpty(orderProduct)){
                    ipnUrlResponse.RspCode = Constant.PAYMENT.VNPAY.RESCODE.ORDER_NOT_FOUND;
                    ipnUrlResponse.Message = Constant.PAYMENT.VNPAY.MESSAGE.ORDER_NOT_FOUND;
                    return ipnUrlResponse;
                }
                if(!paymentCheckout.getTransStatus().equalsIgnoreCase(Constant.PAYMENT.TRANS.WAITING)){
                    ipnUrlResponse.RspCode = Constant.PAYMENT.VNPAY.RESCODE.CONFIRMED;
                    ipnUrlResponse.Message = Constant.PAYMENT.VNPAY.MESSAGE.CONFIRMED;
                    return ipnUrlResponse;
                }
                PaymentMethod paymentMethod = payMentMethodRepository.findByMedthodCode(Constant.PAYMENT.PARTNER.VNPAY);
                if(ObjectUtils.isEmpty(paymentMethod)) {
                    ipnUrlResponse.RspCode =  Constant.PAYMENT.VNPAY.RESCODE.ORDER_NOT_FOUND;
                    ipnUrlResponse.Message = Constant.PAYMENT.VNPAY.MESSAGE.ORDER_NOT_FOUND;
                    return ipnUrlResponse;
                }
                //Check checksum
                boolean checkRequest = PaymentUtils.checkResponseVnpay(request, paymentMethod.getRawPassword(), "FCAWMBRRSPUMHRBFDGCEDOTFDGEORZIV");
                //Check strans status
                if(checkRequest) {
                    if(Constant.PAYMENT.TRANS.SUCCESS.equals(request.getVnp_ResponseCode())){
                        PaymentCheckout checkout = payMentCheckoutRepository.getWaitingByCode(request.getVnp_TxnRef());
                        PaymentCheckout checkout1 = payMentCheckoutRepository.getConfirmByCode(request.getVnp_TxnRef());
                        if(!ObjectUtils.isEmpty(checkout1)){
                            if(!checkout.getCheckoutUrl().contains(request.getVnp_Amount())){
                                ipnUrlResponse.setRspCode(Constant.PAYMENT.VNPAY.RESCODE.INVALID_AMOUNT);
                                ipnUrlResponse.setMessage(Constant.PAYMENT.VNPAY.MESSAGE.INVALID_AMOUNT);
                                return ipnUrlResponse;
                            } else {
                                ipnUrlResponse.RspCode = Constant.PAYMENT.VNPAY.RESCODE.CONFIRMED;
                                ipnUrlResponse.Message = Constant.PAYMENT.VNPAY.MESSAGE.CONFIRMED;
                                return ipnUrlResponse;
                            }
                        }
                        paymentCheckout.setTransStatus(Constant.PAYMENT.TRANS.SUCCESS);
                        paymentCheckout.setPaidAt(DateUtils.getYmdHis());
                        paymentCheckout.setPartnerOrderCode(request.getVnp_TransactionNo());

                        if(!ObjectUtils.isEmpty(paymentCheckout)) {
                            PaymentCheckoutHis his = new PaymentCheckoutHis(checkout);
                            his.setTransactionNo(request.getVnp_TransactionNo());
                            payMentCheckoutHisRepository.save(his);
                        }
                        orderProduct.setStatusPay(Constant.PAID);
                        orderProductRepository.save(orderProduct);
                    } else {
                        paymentCheckout.setTransStatus(Constant.PAYMENT.TRANS.FAILED);
                    }
                }
                payMentCheckoutRepository.save(paymentCheckout);
                ipnUrlResponse.RspCode = Constant.PAYMENT.VNPAY.RESCODE.DONE;
                ipnUrlResponse.Message = Constant.PAYMENT.VNPAY.MESSAGE.SUCCESS;
                return ipnUrlResponse;

            } catch (Exception e) {
                log.error(e);
                ipnUrlResponse.RspCode = Constant.PAYMENT.VNPAY.RESCODE.CHECK_SUM_FAIL;
                ipnUrlResponse.Message = Constant.PAYMENT.VNPAY.MESSAGE.CHECK_SUM_FAIL;
                return ipnUrlResponse;
            }
        } catch (Exception e) {
            log.error(e);
            ipnUrlResponse.RspCode = Constant.PAYMENT.VNPAY.RESCODE.OTHER_ERROR;
            ipnUrlResponse.Message = Constant.PAYMENT.VNPAY.MESSAGE.OTHER_ERROR;
            return ipnUrlResponse;
        }
    }

    @Override
    public ReturnUrlResponse returnUrlVnPay(IpnUrlRequest request) throws ApplicationException {
        ReturnUrlResponse returnUrlResponse = new ReturnUrlResponse();
        try {
            String transDesc = "";
            PaymentCheckout paymentCheckout = payMentCheckoutRepository.getByOrderCode(request.getVnp_TxnRef());
            OrderProduct orderProduct = orderProductRepository.findByOrderCode(request.getVnp_TxnRef());

            if(ObjectUtils.isEmpty(paymentCheckout) || ObjectUtils.isEmpty(orderProduct)) {
                transDesc = "Giao Dịch Thất bại do không được tìm thấy giao dịch được confirm";
                returnUrlResponse.tnxRef = request.getVnp_TxnRef();
                returnUrlResponse.amount = request.getVnp_Amount();
                returnUrlResponse.bankCode = request.getVnp_BankCode();
                returnUrlResponse.time = LocalDateTime.now();
                returnUrlResponse.status = transDesc;
                return returnUrlResponse;
            }

            if(!paymentCheckout.getTransStatus().equalsIgnoreCase(Constant.PAYMENT.TRANS.WAITING)){
                transDesc = "Giao Dịch Thất bại do giao dịch đã được confirm";
                returnUrlResponse.tnxRef = request.getVnp_TxnRef();
                returnUrlResponse.amount = request.getVnp_Amount();
                returnUrlResponse.bankCode = request.getVnp_BankCode();
                returnUrlResponse.time = LocalDateTime.now();
                returnUrlResponse.status = transDesc;
                return returnUrlResponse;
            }

            PaymentMethod paymentMethod = payMentMethodRepository.findByMedthodCode(Constant.PAYMENT.PARTNER.VNPAY);
            if(ObjectUtils.isEmpty(paymentMethod)){
                transDesc = "Giao Dịch Thất bại do không được tìm thấy giao dịch được confirm";
                returnUrlResponse.tnxRef = request.getVnp_TxnRef();
                returnUrlResponse.amount = request.getVnp_Amount();
                returnUrlResponse.bankCode = request.getVnp_BankCode();
                returnUrlResponse.time = LocalDateTime.now();
                returnUrlResponse.status = transDesc;
                return returnUrlResponse;
            }

            Boolean checkRequest = false;
            try {
                checkRequest = PaymentUtils.checkResponseVnpay(request, paymentMethod.getRawPassword(),"FCAWMBRRSPUMHRBFDGCEDOTFDGEORZIV");
            } catch (Exception e) {
                transDesc = e.getMessage();
            }

            if(checkRequest) {
                if(Constant.PAYMENT.TRANS.SUCCESS.equals(request.getVnp_ResponseCode())) {
                    PaymentCheckout checkout = payMentCheckoutRepository.getWaitingByCode(request.getVnp_TxnRef());
                    if(!checkout.getCheckoutUrl().contains(request.getVnp_Amount())) {
                        transDesc = "Giao Dịch Thất bại do số tiền không hợp lệ";
                        returnUrlResponse.tnxRef = request.getVnp_TxnRef();
                        returnUrlResponse.amount = request.getVnp_Amount();
                        returnUrlResponse.bankCode = request.getVnp_BankCode();
                        returnUrlResponse.time = LocalDateTime.now();
                        returnUrlResponse.status = transDesc;
                        return returnUrlResponse;
                    }

                    List<ReturnVnPayLog> vnPayLogList = returnVnpayLogRepository.checkConfirm(request.getVnp_TxnRef());
                    if(!ObjectUtils.isEmpty(vnPayLogList)){
                        transDesc = "Giao Dịch Thất bại do giao dịch đã được confirm";
                        returnUrlResponse.tnxRef = request.getVnp_TxnRef();
                        returnUrlResponse.amount = request.getVnp_Amount();
                        returnUrlResponse.bankCode = request.getVnp_BankCode();
                        returnUrlResponse.time = LocalDateTime.now();
                        returnUrlResponse.status = transDesc;
                        return returnUrlResponse;
                    }
                    transDesc = "Giao Dịch Thành công";
                }
            } else {
                transDesc = "Giao Dịch Thất Bại";
            }

            Gson gson = new Gson();
            log.info(gson.toJson(request));
            returnUrlResponse.tnxRef = request.getVnp_TxnRef();
            returnUrlResponse.amount = request.getVnp_Amount();
            returnUrlResponse.bankCode = request.getVnp_BankCode();
            returnUrlResponse.time = LocalDateTime.now();
            returnUrlResponse.status = transDesc;
            try {
                ReturnVnPayLog returnVnPayLog = new ReturnVnPayLog();
                returnVnPayLog.setCreateAt(LocalDateTime.now());
                returnVnPayLog.setOrderCode(request.getVnp_TxnRef());
                returnVnPayLog.setRequest(gson.toJson(request));
                returnVnPayLog.setResponse(gson.toJson(returnUrlResponse));
                returnVnPayLog.setPartnerTransStatus(transDesc);
                returnVnpayLogRepository.save(returnVnPayLog);
            } catch (Exception e) {
                log.error(e);
            }
            return returnUrlResponse;

        } catch (Exception e) {
            throw new ApplicationException("error");
        }
    }

    private PaymentCheckout setUpRequestPay (VnPayRequest req) throws ApplicationException {
        try {
            int amount = (int) (req.getAmount() * 100);
            Map<String, String> vnpParamMap = new HashMap<>();
            vnpParamMap.put("vnp_Version", req.getVersion());
            vnpParamMap.put("vnp_Command", Constant.PAYMENT.VNPAY.COMMAND.PAY);
            vnpParamMap.put("vnp_TmnCode", req.getTmnCode());
            vnpParamMap.put("vnp_Amount", String.valueOf(amount));
            vnpParamMap.put("vnp_CurrCode", "VND");
            vnpParamMap.put("vnp_TxnRef", req.getOrderCode());
            vnpParamMap.put("vnp_OrderInfo", req.getVnpOrderInfo());
            vnpParamMap.put("vnp_OrderType", req.getOrderType());

            String locate = req.getLanguage();
            if (locate != null && !locate.isEmpty()) {
                vnpParamMap.put("vnp_Locale", locate);
            } else {
                vnpParamMap.put("vnp_Locale", "vn");
            }
            vnpParamMap.put("vnp_ReturnUrl", req.getReturnUrl());
            vnpParamMap.put("vnp_IpAddr", req.getIpAddress());

            LocalDateTime dt = LocalDateTime.now();
            String formatter = "yyyyMMddHHmmss";
            String dateString = DateUtils.parseDateToString(dt,formatter);
            vnpParamMap.put("vnp_CreateDate", dateString);
            HashResponse hashResponse = getSecureHash(vnpParamMap, req.getHashKey());

            String queryUrl = hashResponse.getQueryUrl();
            queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + hashResponse.getVnpSecureHash();
            String paymentUrl = req.getPayUrl() + "?" + queryUrl;
            log.info("paymentUrl: " + paymentUrl);

            PaymentCheckout paymentCheckout = new PaymentCheckout();
            paymentCheckout.setCheckoutUrl(paymentUrl);
            paymentCheckout.setOrderCode(req.getOrderCode());
            paymentCheckout.setTransStatus(Constant.PAYMENT.TRANS.WAITING);
            paymentCheckout.setCreatedAt(DateUtils.parseDateToStringDefault(dt));
            paymentCheckout.setMethodCode(Constant.PAYMENT.PARTNER.VNPAY);

            // Return response
            return paymentCheckout;
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("ERR_0000307");
        }
    }

    private HashResponse getSecureHash(Map vnpParamMap, String hashKey) throws ApplicationException{
        try {
            //Build data to hash and querystring
            List fieldNames = new ArrayList(vnpParamMap.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnpParamMap.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(fieldValue);
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            log.info("query: " + queryUrl);
            log.info("hashKey: " + hashKey);
            String vnpSecureHash = PaymentUtils.Sha256VnPay(hashKey + hashData.toString());
            log.info("HashData= " + hashData.toString());
            HashResponse hashResponse = new HashResponse();
            hashResponse.setQueryUrl(queryUrl);
            hashResponse.setVnpSecureHash(vnpSecureHash);
            return hashResponse;
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("ERR_0000309");
        }
    }
}
