package com.hitex.yousim.dto.request.function;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Function;
import lombok.Data;

@Data
public class FunctionRequest extends Function implements IRequestData {
    private int page;
    private int pageSize;
    @Override
    public boolean isValid() {
        return false;
    }
}
