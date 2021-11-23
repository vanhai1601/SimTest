package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.assessment.AssessmentResponse;
import com.hitex.yousim.dto.response.assessment.ListAssessmentResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface AssessmentService {
    AssessmentResponse addAssessment(BaseRequestData baseRequestData) throws ApplicationException;
    ListAssessmentResponse getAllAssessment(BaseRequestData baseRequestData) throws ApplicationException;
}
