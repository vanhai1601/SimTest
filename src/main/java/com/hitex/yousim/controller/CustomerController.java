package com.hitex.yousim.controller;

import com.hitex.yousim.SocialLoginApi;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.customer.CustomerRequest;
import com.hitex.yousim.dto.request.customer.SocialTokenRequest;
import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.dto.response.customer.CustomerResponse;
import com.hitex.yousim.repository.CustomerRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.CustomerService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CustomerController extends BaseController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SocialLoginApi socialLoginApi;
    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/socialLogin")
    @ResponseBody
    public ResponseEntity<?> loginFb(@RequestBody BaseRequestData<SocialTokenRequest> request) throws ApplicationException {
        SocialTokenRequest socialTokenRequest = request.getWsRequest();
        String accessToken = socialTokenRequest.getAccessToken();
        String provider = request.getWsRequest().getProvider();
        BaseResponseData response = socialLoginApi.excute(accessToken, provider);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("updateCustomer")
    public ResponseEntity updateCustomer(@RequestBody BaseRequestData<CustomerRequest> requestData) throws ApplicationException {
        try {
            CustomerResponse customerResponse = customerService.updateCustomer(requestData);
            return success(customerResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
