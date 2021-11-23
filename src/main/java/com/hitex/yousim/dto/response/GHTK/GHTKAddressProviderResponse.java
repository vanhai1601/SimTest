package com.hitex.yousim.dto.response.GHTK;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;


@Data
public class GHTKAddressProviderResponse implements IResponseData {
    private String address;
    private String phone;
    private String name;
    private String addressId;
}
