package com.hitex.yousim.dto.request.roleFunction;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.RoleFunction;
import lombok.Data;

@Data
public class RoleFunctionRequest extends RoleFunction implements IRequestData {
    private int page;
    private int pageSize;
    @Override
    public boolean isValid() {
        return false;
    }
}
