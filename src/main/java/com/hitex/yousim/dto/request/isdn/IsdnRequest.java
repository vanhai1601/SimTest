package com.hitex.yousim.dto.request.isdn;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Isdn;

public class IsdnRequest extends Isdn implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
