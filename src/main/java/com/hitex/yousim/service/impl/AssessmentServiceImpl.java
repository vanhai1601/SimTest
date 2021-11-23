package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.assessment.AssessmentRequest;
import com.hitex.yousim.dto.response.assessment.AssessmentResponse;
import com.hitex.yousim.dto.response.assessment.ListAssessmentResponse;
import com.hitex.yousim.model.Assessment;
import com.hitex.yousim.model.User;
import com.hitex.yousim.model.view.KitAndIsdn;
import com.hitex.yousim.model.view.ViewSimPackage;
import com.hitex.yousim.repository.AssessmentRepository;
import com.hitex.yousim.repository.KitAndIsdnRepository;
import com.hitex.yousim.repository.SimPackageRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.AssessmentService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public AssessmentResponse addAssessment(BaseRequestData baseRequestData) throws ApplicationException {
        AssessmentResponse assessmentResponse = new AssessmentResponse();
        AssessmentRequest request = (AssessmentRequest) baseRequestData.getWsRequest();
        try {
            User userLogin = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(),baseRequestData.getToken());
            Assessment assessment = new Assessment();

            if(ObjectUtils.isEmpty(userLogin)){
                throw new ApplicationException("ERR_0000003");
            }
            if(request.getStarNumber() <= 0) {
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("star"));
            }
            if(StringUtils.isEmpty(request.getTitle())) {
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("title"));
            }
            if(StringUtils.isEmpty(request.getContent())) {
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("content"));
            }
            assessment.setUserName(userLogin.getUserName());
            assessment.setStarNumber(request.getStarNumber());
            assessment.setTitle(request.getTitle());
            assessment.setContent(request.getContent());
            assessment.setCreateTime(LocalDateTime.now());
            assessmentRepository.save(assessment);
            BeanUtils.copyProperties(assessment,assessmentResponse);
        } catch (ApplicationException e) {
            throw e;
        }
        return assessmentResponse;
    }

    @Override
    public ListAssessmentResponse getAllAssessment(BaseRequestData baseRequestData) throws ApplicationException {
        ListAssessmentResponse listAssessmentResponse = new ListAssessmentResponse();
        AssessmentRequest assessmentRequest = (AssessmentRequest) baseRequestData.getWsRequest();
        try {
            List<Assessment> assessmentList = assessmentRepository.findAllAssessment(PageRequest.of(assessmentRequest.getPage(),assessmentRequest.getPageSize()));
            List<AssessmentResponse>  assessmentResponseList = new ArrayList<>();
            for(Assessment assessment: assessmentList){
                AssessmentResponse assessmentResponse = new AssessmentResponse();
                BeanUtils.copyProperties(assessment,assessmentResponse);
                assessmentResponseList.add(assessmentResponse);
            }
            int totalItem = assessmentRepository.countAlltAssessment();
            int totalPage = (int) Math.ceil((double) totalItem / (double) assessmentRequest.getPageSize());
            listAssessmentResponse.setTotalItem(totalItem);
            listAssessmentResponse.setTotalPage(totalPage);
            listAssessmentResponse.setAssessmentResponseList(assessmentResponseList);
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
        return listAssessmentResponse;
    }
}
