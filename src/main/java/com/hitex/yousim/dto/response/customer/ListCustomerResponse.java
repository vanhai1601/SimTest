package com.hitex.yousim.dto.response.customer;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListCustomerResponse implements IResponseData {
	int totalItem;
	int totalPage;
	List<CustomerResponse> findAllCustomer;
}
