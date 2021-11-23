package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.question.QuestionRequest;
import com.hitex.yousim.dto.response.question.QuestionReponse;
import com.hitex.yousim.model.Question;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.QuestionRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.QuestionService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public QuestionReponse addQuestion(BaseRequestData baseRequestData) throws ApplicationException {
        QuestionReponse questionReponse = new QuestionReponse();
        QuestionRequest questionRequest =(QuestionRequest) baseRequestData.getWsRequest();
        try {
            User userLogin = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(),baseRequestData.getToken());
            if(ObjectUtils.isEmpty(userLogin)){
                throw new ApplicationException("ERR_0000003");
            }
            if(StringUtils.isEmpty(questionRequest.getQuestion())){
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("question"));
            }
            Question question = new Question();
            question.setUserName(userLogin.getUserName());
            question.setEmail(userLogin.getEmail());
            question.setQuestion(questionRequest.getQuestion());
            question.setStatus(1);
            question.setCreateTime(LocalDateTime.now());
            questionRepository.save(question);

            BeanUtils.copyProperties(question, questionReponse);

        } catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }

        return questionReponse;
    }

    @Override
    public QuestionReponse updateStatusQuestion(BaseRequestData baseRequestData) throws ApplicationException {
        QuestionReponse questionReponse = new QuestionReponse();
        QuestionRequest questionRequest = (QuestionRequest) baseRequestData.getWsRequest();
        try {
            User userLogin = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(),baseRequestData.getToken());
            if(ObjectUtils.isEmpty(userLogin)){
                throw new ApplicationException("ERR_0000003");
            }
            if(userLogin.getRoleId() !=  1){
                throw new ApplicationException("ERR_0000401");
            }
            Question questionUpdate = questionRepository.findById(questionRequest.getId());
            questionUpdate.setStatus(2);
            questionRepository.save(questionUpdate);
            BeanUtils.copyProperties(questionUpdate,questionReponse);

        } catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }
        return questionReponse;
    }
}
