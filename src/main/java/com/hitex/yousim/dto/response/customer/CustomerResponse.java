package com.hitex.yousim.dto.response.customer;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.Customer;
import lombok.Data;

@Data
public class CustomerResponse extends Customer implements IResponseData {
        String token;
        String session;
        String hashImgFont;
}
