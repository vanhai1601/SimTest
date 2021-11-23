package com.hitex.yousim.dto.request.nation;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Nation;

public class NationRequest extends Nation implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
