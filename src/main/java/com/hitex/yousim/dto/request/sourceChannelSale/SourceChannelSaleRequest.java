package com.hitex.yousim.dto.request.sourceChannelSale;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;


@Data
public class SourceChannelSaleRequest  implements IRequestData {
    private int page;
    private int pageSize;
    @Override
    public boolean isValid() {
        return false;
    }
}
