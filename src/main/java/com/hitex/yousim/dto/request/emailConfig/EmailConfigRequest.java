package com.hitex.yousim.dto.request.emailConfig;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class EmailConfigRequest implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
