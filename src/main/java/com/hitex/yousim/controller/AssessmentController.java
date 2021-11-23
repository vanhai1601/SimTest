package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.assessment.AssessmentRequest;
import com.hitex.yousim.dto.request.question.QuestionRequest;
import com.hitex.yousim.dto.response.assessment.AssessmentResponse;
import com.hitex.yousim.dto.response.assessment.ListAssessmentResponse;
import com.hitex.yousim.dto.response.question.QuestionReponse;
import com.hitex.yousim.service.AssessmentService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class AssessmentController extends BaseController{
    @Autowired
    private AssessmentService assessmentService;

    @PostMapping("/addAssessment")
    public ResponseEntity addAssessment(@RequestBody BaseRequestData<AssessmentRequest> baseRequestData) throws ApplicationException {
        try {
            AssessmentResponse assessmentResponse = assessmentService.addAssessment(baseRequestData);
            return success(assessmentResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/getAllAssessment")
    public ResponseEntity<?> getAllAssessment(@RequestBody BaseRequestData<AssessmentRequest> baseRequestData) throws ApplicationException {
        try {
            ListAssessmentResponse listAssessmentResponse = assessmentService.getAllAssessment(baseRequestData);
            return success(listAssessmentResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
