package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.question.QuestionRequest;
import com.hitex.yousim.dto.response.contact.ContactReponse;
import com.hitex.yousim.dto.response.question.QuestionReponse;
import com.hitex.yousim.service.QuestionService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api")
public class QuestionController extends BaseController{
    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion")
    public ResponseEntity addQuestion(@RequestBody BaseRequestData<QuestionRequest> baseRequestData) throws ApplicationException {

        try {
            QuestionReponse questionReponse = questionService.addQuestion(baseRequestData);
            return success(questionReponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping ("/updateStatusQuestion")
    public ResponseEntity updateStatusQuestion(@RequestBody BaseRequestData<QuestionRequest> baseRequestData) throws ApplicationException {
        try {
            QuestionReponse questionReponse = questionService.updateStatusQuestion(baseRequestData);
            return success(questionReponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
