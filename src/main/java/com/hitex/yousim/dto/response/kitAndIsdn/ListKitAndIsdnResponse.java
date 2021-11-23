package com.hitex.yousim.dto.response.kitAndIsdn;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;
@Data
public class ListKitAndIsdnResponse implements IResponseData {
    private int totalPage;
    private int totalItem;
    private List<KitAndIsdnResponse> kitAndIsdnResponseList;

}
