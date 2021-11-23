package com.hitex.yousim.dto.request.role;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Role;
import lombok.Data;

@Data
public class RoleRequest extends Role implements IRequestData {
    private String roleName;
    private int page;
    private int pageSize;
    @Override
    public boolean isValid() {
        return false;
    }
}
