package com.hitex.yousim.dto.response.isdn;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListIsdnRespone implements IResponseData {
    private int totalPage;
    private int totalItem;
    List<IsdnRespone> isdnDtos;

}
