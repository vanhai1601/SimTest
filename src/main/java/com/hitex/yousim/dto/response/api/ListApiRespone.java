package com.hitex.yousim.dto.response.api;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListApiRespone implements IResponseData {
    private int totalItem;
    private int totalPage;

    List<ApiRespone> apiResponeList;
}
