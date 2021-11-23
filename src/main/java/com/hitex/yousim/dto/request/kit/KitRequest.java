package com.hitex.yousim.dto.request.kit;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.KIT;
import lombok.Data;

@Data
public class KitRequest extends KIT implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
