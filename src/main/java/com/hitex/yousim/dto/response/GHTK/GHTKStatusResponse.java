package com.hitex.yousim.dto.response.GHTK;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

@Data
public class GHTKStatusResponse implements IResponseData {
    private String ghtkId;
    private String statusGHTK;
    private String statusGHTK_text;
    private String fileOrder;
}
