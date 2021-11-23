package com.hitex.yousim.dto.request.export;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class ExportListOrderRequest  implements IRequestData {

    private String startTime;
    private String endTime;

    @Override
    public boolean isValid() {
        return false;
    }
}
