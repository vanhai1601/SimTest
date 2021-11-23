package com.hitex.yousim.dto.request.simPackage;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.view.ViewSimPackage;
import lombok.Data;

@Data
public class SimPackageRequest extends ViewSimPackage implements IRequestData {
    private int page;
    private int pageSize;
    private  int sortPrice;
    private int sortDate;
    @Override
    public boolean isValid() {
        return false;
    }
}
