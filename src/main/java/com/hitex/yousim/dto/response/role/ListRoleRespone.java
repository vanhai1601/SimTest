package com.hitex.yousim.dto.response.role;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class ListRoleRespone implements IResponseData {
    int totalItem;
    int totalPage;
    List<Role> lstRole;
}
