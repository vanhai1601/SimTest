package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.customer.CustomerRequest;
import com.hitex.yousim.dto.request.customer.SocialAccountInfo;
import com.hitex.yousim.dto.response.customer.CustomerResponse;
import com.hitex.yousim.model.Customer;
import com.hitex.yousim.repository.CustomerRepository;
import com.hitex.yousim.service.CustomerService;
import com.hitex.yousim.utils.GenCodeUtils;
import com.hitex.yousim.utils.exception.ApplicationException;

import java.sql.Date;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;


    @Override
    @Transactional
    public CustomerResponse loginSocialAccount(SocialAccountInfo accountInfo) throws ApplicationException {
        CustomerResponse customerResponse = new CustomerResponse();
        try {
            Customer customer = customerRepository.findCustomerByProviderAndProvider_id(accountInfo.getProvider(), accountInfo.getId());
            if (customer == null) {
                customer = new Customer();
                customer.setCusName(accountInfo.getName());
                customer.setCreateTime(LocalDateTime.now());
                customer.setUpdateTime(LocalDateTime.now());
                customer.setProvider_id(accountInfo.getId());
                customer.setProvider(accountInfo.getProvider());
                customer.setStatus(2);
                customerRepository.save(customer);
                String tokenData = GenCodeUtils.encrypt(customer.getCusName() + "_" + customer.getStatus()
                        + "_" + customer.getCustId(), Constant.KEY, Constant.SECRET_KEY);
                customer.setToken(tokenData);
                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setMaxInactiveInterval(60 * 60 * 24);
                customer.setSession(httpSession.getId());
                customerRepository.save(customer);
                BeanUtils.copyProperties(customer, customerResponse);
            } else {
                String tokenData = GenCodeUtils.encrypt(customer.getCusName() + "_" + customer.getStatus()
                        + "_" + customer.getCustId(), Constant.KEY, Constant.SECRET_KEY);
                customer.setToken(tokenData);
                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setMaxInactiveInterval(60 * 60 * 24);
                customer.setSession(httpSession.getId());
                customerRepository.save(customer);
                BeanUtils.copyProperties(customer, customerResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerResponse;
    }

    @Override
    public CustomerResponse detailCustomer(CustomerRequest customerRequest) throws ApplicationException {

        CustomerResponse customerResponse = new CustomerResponse();
        try {
            Customer customer = customerRepository.findCustomerById(customerRequest.getCustId());
            BeanUtils.copyProperties(customer, customerResponse);
        } catch (Exception e) {
            throw e;
        }
        return customerResponse;
    }

    @Override
    public CustomerResponse updateCustomer(BaseRequestData requestData) throws ApplicationException {
        CustomerRequest customerRequest = (CustomerRequest) requestData.getWsRequest();
        CustomerResponse customerResponse = new CustomerResponse();
        Customer customer = new Customer();
        try {
            Customer checkCustomer = customerRepository.findCustomerByCustomerEmail(customerRequest.getEmail());
            if (ObjectUtils.isEmpty(checkCustomer)) {
                BeanUtils.copyProperties(customerRequest, customer);
                customerRepository.save(customer);
                BeanUtils.copyProperties(customer, customerResponse);
            } else {
                Customer customerUpdate = customerRepository.findCustomerByCustomerEmail(customerRequest.getEmail());
                customerUpdate.setPhone(customerRequest.getPhone());
                customerUpdate.setAvatar(customerRequest.getAvatar());
                customerUpdate.setAddress(customerRequest.getAddress());
                customerUpdate.setSex(customerRequest.getSex());
                customerUpdate.setDateOfBirth(customerRequest.getDateOfBirth());
                customerUpdate.setIdCard(customerRequest.getIdCard());
                customerRepository.save(customerUpdate);
                BeanUtils.copyProperties(customerUpdate, customerResponse);
            }
            throw new ApplicationException("error");
        } catch (ApplicationException e) {
            e.getLocalizedMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerResponse;
    }
}
