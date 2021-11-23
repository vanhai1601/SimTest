package com.hitex.yousim.dto.request.api;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Api;
import lombok.Data;

@Data
public class ApiRequest extends Api implements IRequestData {
    private int page;
    private int pageSize;
    @Override
    public boolean isValid() {
        return false;
    }
}
