package com.hitex.yousim.dto.response.GHTK;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

@Data
public class GHTKCodeResponse implements IResponseData {
    private String orderGHTKCode;
}
