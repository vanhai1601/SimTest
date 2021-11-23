package com.hitex.yousim.dto.request.GHTK;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class GHTKRequest implements IRequestData {
    private String orderGHTKCode;
    private String orderCode;
    private String nameProductGHTK;
    private String pick_province;
    private String pick_district;
    private String pick_ward;
    private String session;
    private String token;
    @Override
    public boolean isValid() {
        return false;
    }
}
