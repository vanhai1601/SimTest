package com.hitex.yousim.dto.request.physicType;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.PhysicType;
import lombok.Data;

@Data
public class PhysicTypeRequest extends PhysicType implements IRequestData {

    @Override
    public boolean isValid() {
        return false;
    }
}
