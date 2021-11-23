package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.contact.ContactReponse;
import com.hitex.yousim.dto.response.question.QuestionReponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface QuestionService {
    QuestionReponse addQuestion(BaseRequestData baseRequestData) throws ApplicationException;
    QuestionReponse updateStatusQuestion(BaseRequestData baseRequestData) throws ApplicationException;
}
