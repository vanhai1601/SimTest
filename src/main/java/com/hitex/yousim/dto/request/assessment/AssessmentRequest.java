package com.hitex.yousim.dto.request.assessment;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Assessment;
import lombok.Data;

@Data
public class AssessmentRequest extends Assessment implements IRequestData {
    private int page;
    private int pageSize;
    @Override
    public boolean isValid() {
        return false;
    }
}
