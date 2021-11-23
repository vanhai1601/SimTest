package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.servicepage.ListServiceLanguageResponse;
import com.hitex.yousim.dto.response.servicepage.ServiceLanguageResponse;
import com.hitex.yousim.model.ServicePage;
import com.hitex.yousim.model.ServicePageLanguage;
import com.hitex.yousim.repository.ServicePageLanguageRepository;
import com.hitex.yousim.repository.ServicePageRepository;
import com.hitex.yousim.service.ServicePageLanguageService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePageLanguageImpl implements ServicePageLanguageService {
    @Autowired
    ServicePageLanguageRepository servicePageLanguageRepository;
    @Autowired
    ServicePageRepository servicePageRepository;

    @Override
    public ListServiceLanguageResponse getServiceLang(BaseRequestData request) throws ApplicationException {
        ListServiceLanguageResponse serviceLanguageResponseList = new ListServiceLanguageResponse();

        List<ServiceLanguageResponse> responseList = new ArrayList<>();

        List<ServicePageLanguage> list = servicePageLanguageRepository.getService(String.valueOf(LocaleContextHolder.getLocale()));
        try {
            if (list.size() == 0) {
                throw new ApplicationException("ERR_0000109");
            } else {
                for (ServicePageLanguage servicePageLanguage : list) {
                    ServiceLanguageResponse serviceLanguageResponse = new ServiceLanguageResponse();

                    ServicePage servicePage = servicePageRepository.getServicePageById(servicePageLanguage.getServiceId());
                    BeanUtils.copyProperties(servicePage, serviceLanguageResponse);

                    BeanUtils.copyProperties(servicePageLanguage, serviceLanguageResponse);
                    responseList.add(serviceLanguageResponse);
                }
                serviceLanguageResponseList.setGetListServiceLanguageResponse(responseList);
            }
        } catch (Exception e) {
            throw e;
        }
        return serviceLanguageResponseList;
    }
}
