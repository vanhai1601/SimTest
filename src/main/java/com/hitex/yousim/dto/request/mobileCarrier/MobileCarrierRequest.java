package com.hitex.yousim.dto.request.mobileCarrier;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.MobileCarrier;

public class MobileCarrierRequest extends MobileCarrier implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
