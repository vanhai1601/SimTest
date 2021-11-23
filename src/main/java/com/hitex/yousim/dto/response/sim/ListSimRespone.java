package com.hitex.yousim.dto.response.sim;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListSimRespone implements IResponseData {
    private int totalPage;
    private int totalItem;

    List<SimRespone> simResponeList;
}
