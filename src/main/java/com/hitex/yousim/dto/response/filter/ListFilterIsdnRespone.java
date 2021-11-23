package com.hitex.yousim.dto.response.filter;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;


@Data
public class ListFilterIsdnRespone implements IResponseData {
    int totalItem;
    int totalPage;
//    List<ListNumber> listNumbers;
}
