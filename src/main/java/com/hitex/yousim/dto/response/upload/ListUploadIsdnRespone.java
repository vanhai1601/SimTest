package com.hitex.yousim.dto.response.upload;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.Isdn;
import lombok.Data;

import java.util.List;

@Data
public class ListUploadIsdnRespone implements IResponseData {
    int totalItem;
    int totalPage;
    List<Isdn> isdnList;
}
