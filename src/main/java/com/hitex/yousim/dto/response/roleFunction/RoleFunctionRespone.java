package com.hitex.yousim.dto.response.roleFunction;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.RoleFunction;
import lombok.Data;

@Data
public class RoleFunctionRespone extends RoleFunction implements IResponseData {
    private String nameApi;
}
