package com.hitex.yousim.dto.response.function;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListFunctionRespone implements IResponseData {
    private int totalItem;
    private int totalPage;
    List<FunctionRespone> functionResponeList;
}
