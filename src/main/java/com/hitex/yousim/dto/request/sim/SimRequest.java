package com.hitex.yousim.dto.request.sim;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Sim;
import lombok.Data;

@Data
public class SimRequest extends Sim implements IRequestData {
    private int page;
    private int pageSize;
    @Override
    public boolean isValid() {
        return false;
    }
}
