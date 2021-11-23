package com.hitex.yousim.dto.request.firstNumberSim;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.FirstNumberSim;

public class FirstNumberSimRequest extends FirstNumberSim implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
