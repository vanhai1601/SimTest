package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.customer.CustomerRequest;
import com.hitex.yousim.dto.request.customer.SocialAccountInfo;
import com.hitex.yousim.dto.response.customer.CustomerResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface CustomerService {
    CustomerResponse loginSocialAccount(SocialAccountInfo accountInfo) throws ApplicationException;

    CustomerResponse detailCustomer(CustomerRequest customerRequest) throws ApplicationException;
    //    CustomerResponse addCustomer(CustomerRequest customerRequest) throws ApplicationException ;

    CustomerResponse updateCustomer(BaseRequestData requestData) throws ApplicationException;
}
