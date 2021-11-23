package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.footer.FooterLanguageRequest;
import com.hitex.yousim.dto.request.footer.FooterRequest;
import com.hitex.yousim.dto.response.footer.FooterLanguageResponse;
import com.hitex.yousim.model.Footer;
import com.hitex.yousim.model.FooterLanguage;
import com.hitex.yousim.repository.FooterLanguageRepository;
import com.hitex.yousim.repository.FooterRepository;
import com.hitex.yousim.service.FooterLanguageService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FooterLanguageServiceImpl implements FooterLanguageService {
    @Autowired
    FooterLanguageRepository footerLanguageRepository;
    @Autowired
    FooterRepository footerRepository;

    @Override
    public FooterLanguageResponse getFooter(BaseRequestData request) throws ApplicationException {
        FooterLanguageResponse footerLanguageResponse = new FooterLanguageResponse();
        try {
            FooterLanguage footerLanguage = footerLanguageRepository.getFooter(String.valueOf(LocaleContextHolder.getLocale()));
            BeanUtils.copyProperties(footerLanguage, footerLanguageResponse);

            Footer footer = footerRepository.getFooterById(footerLanguage.getFooterId());
            BeanUtils.copyProperties(footer, footerLanguageResponse);

        } catch (Exception e) {
            throw e;
        }
        return footerLanguageResponse;
    }

    @Override
    public FooterLanguageResponse addLanguage(BaseRequestData request) throws ApplicationException {
        FooterLanguageResponse footerLanguageResponse = new FooterLanguageResponse();
        FooterLanguageRequest footerLanguageRequest = (FooterLanguageRequest) request.getWsRequest();
        try {
            FooterLanguage footerLanguage = new FooterLanguage();
            footerLanguage.setCodeLanguage(footerLanguageRequest.getCodeLanguage());
            footerLanguage.setAddress(footerLanguageRequest.getAddress());
            footerLanguage.setDescriptions(footerLanguageRequest.getDescriptions());
            footerLanguage.setBusinessCode(footerLanguageRequest.getBusinessCode());
            footerLanguage.setOwner(footerLanguageRequest.getOwner());
            footerLanguage.setResponsible(footerLanguageRequest.getResponsible());
            footerLanguage.setFooterId(footerLanguageRequest.getFooterId());
            BeanUtils.copyProperties(footerLanguageRequest, footerLanguage);
            footerLanguageRepository.save(footerLanguage);
            BeanUtils.copyProperties(footerLanguage, footerLanguageResponse);

            Footer footer = footerRepository.getFooterById(footerLanguage.getFooterId());
            BeanUtils.copyProperties(footer, footerLanguageResponse);
        } catch (Exception e) {
            throw e;
        }
        return footerLanguageResponse;
    }

    @Override
    public FooterLanguageResponse updateFooter(BaseRequestData request) throws ApplicationException {
        FooterLanguageResponse footerLanguageResponse = new FooterLanguageResponse();
        FooterLanguageRequest footerLanguageRequest = (FooterLanguageRequest) request.getWsRequest();
        FooterRequest footerRequest = (FooterRequest) request.getWsRequest();
        try {
            FooterLanguage footerLanguage = footerLanguageRepository.getFooter(String.valueOf(LocaleContextHolder.getLocale()));
            footerLanguage.setCodeLanguage(footerLanguageRequest.getCodeLanguage());
            footerLanguage.setAddress(footerLanguageRequest.getAddress());
            footerLanguage.setDescriptions(footerLanguageRequest.getDescriptions());
            footerLanguage.setBusinessCode(footerLanguageRequest.getBusinessCode());
            footerLanguage.setOwner(footerLanguageRequest.getOwner());
            footerLanguage.setResponsible(footerLanguageRequest.getResponsible());
            footerLanguage.setFooterId(footerLanguageRequest.getFooterId());
            BeanUtils.copyProperties(footerLanguageRequest, footerLanguage);
            footerLanguageRepository.save(footerLanguage);
            BeanUtils.copyProperties(footerLanguage, footerLanguageResponse);

            Footer footer = footerRepository.getFooterById(footerLanguage.getFooterId());
            footer.setHotline(footerRequest.getHotline());
            footer.setEmail(footerRequest.getEmail());
            footer.setStatus(footerRequest.isStatus());
            footerRepository.save(footer);
            BeanUtils.copyProperties(footer, footerLanguageResponse);
        } catch (Exception e) {
            throw e;
        }
        return footerLanguageResponse;
    }


}
