package com.hitex.yousim.dto.request.kitAndIsdn;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.view.KitAndIsdn;
import lombok.Data;

@Data
public class KitAndIsdnRequest extends KitAndIsdn implements IRequestData {
    private String isdnFirst;
    private int page;
    private int pageSize;
    private int randomSize;
    private int toStockModelId;
    private  int sortPrice;
    private int sortDate;
    @Override
    public boolean isValid() {
        return false;
    }
}
