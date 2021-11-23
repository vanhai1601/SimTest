package com.hitex.yousim.dto.response.filter;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.KIT;
import lombok.Data;

@Data
public class IsdnRespone extends KIT implements IResponseData {
    private String stockModel;
    private Double amount;
}
