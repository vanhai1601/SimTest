package com.hitex.yousim.dto.response.roleFunction;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListRoleFunctionRespone implements IResponseData {
    private int totalItem;
    private int totalPage;
    private String[] apiName;
    List<RoleFunctionRespone> roleFunctionResponeList;
}
