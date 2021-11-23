package com.hitex.yousim.dto.response.sourceChannelSale;


import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListSourceChannelSaleResponse implements IResponseData {
    private int totalPage;
    private int totalItem;

    List<SourceChannelSaleResponse> sourceChannelSaleResponseList;
}
