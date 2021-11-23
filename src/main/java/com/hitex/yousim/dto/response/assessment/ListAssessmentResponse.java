package com.hitex.yousim.dto.response.assessment;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListAssessmentResponse implements IResponseData {
    private int totalItem;
    private int totalPage;
    private List<AssessmentResponse> assessmentResponseList;
}
