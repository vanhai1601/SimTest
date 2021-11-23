package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.orderProduct.ListOrderProductRequest;
import com.hitex.yousim.dto.request.orderProduct.OrderRequest;
import com.hitex.yousim.dto.response.orderProduct.OrderProductResponse;
import com.hitex.yousim.model.Customer;
import com.hitex.yousim.model.OrderProduct;
import com.hitex.yousim.repository.CustomerRepository;
import com.hitex.yousim.repository.OrderProductRepository;
import com.hitex.yousim.service.OrderService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional(rollbackFor = ApplicationException.class)
    @Override
    public OrderProductResponse addOrder(BaseRequestData baseRequestData) throws ApplicationException {
        OrderProductResponse orderProductResponse = new OrderProductResponse();
        ListOrderProductRequest listOrderProductRequest = (ListOrderProductRequest) baseRequestData.getWsRequest();
        try {
            if (StringUtils.isEmpty(listOrderProductRequest.getPhone())) {
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("phoneNumber"));
            }
            if (StringUtils.isEmpty(listOrderProductRequest.getEmail())) {
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("email"));
            }
            Customer customer = customerRepository.findCustomerByCustomerEmail(listOrderProductRequest.getEmail());
            if (ObjectUtils.isEmpty(customer)) {
                customer = new Customer();
                BeanUtils.copyProperties(listOrderProductRequest, customer);
                customer.setStatus(Constant.ACTIVE_CUSTOMER);
            }
            customer.setCusName(listOrderProductRequest.getCusName());
            customer.setPhone(listOrderProductRequest.getPhone());
            customer.setCityId(listOrderProductRequest.getCityId());
            customer.setVillageId(listOrderProductRequest.getVillageId());
            customer.setDistrictId(listOrderProductRequest.getDistrictId());
            customerRepository.save(customer);
            if (ObjectUtils.isEmpty(listOrderProductRequest.getOrderRequestList())) {
                throw new ApplicationException("error");
            }
            OrderProduct orderProduct = new OrderProduct();
            BeanUtils.copyProperties(listOrderProductRequest, orderProduct);
            if (!StringUtils.isEmpty(listOrderProductRequest.getDeliveryCityId())) {
                orderProduct.setCityId(listOrderProductRequest.getDeliveryCityId());
            }
            if (!StringUtils.isEmpty(listOrderProductRequest.getDeliveryDistrictId())) {
                orderProduct.setDistrictId(listOrderProductRequest.getDeliveryDistrictId());
            }
            if (!StringUtils.isEmpty(listOrderProductRequest.getDeliveryVillageId())) {
                orderProduct.setVillageId(listOrderProductRequest.getDeliveryVillageId());
            }
            if (!StringUtils.isEmpty(listOrderProductRequest.getNameDelivery())) {
                orderProduct.setNameDelivery(listOrderProductRequest.getNameDelivery());
            } else {
                orderProduct.setNameDelivery(listOrderProductRequest.getCusName());
            }
            if (!StringUtils.isEmpty(listOrderProductRequest.getPhoneDelivery())) {
                orderProduct.setPhoneDelivery(listOrderProductRequest.getPhoneDelivery());
            } else {
                orderProduct.setPhoneDelivery(listOrderProductRequest.getPhone());
            }
            for (OrderRequest orderRequest: listOrderProductRequest.getOrderRequestList()) {

            }

            orderProductRepository.save(orderProduct);
        } catch (ApplicationException e) {
            throw e;
        }
        return orderProductResponse;
    }
}
