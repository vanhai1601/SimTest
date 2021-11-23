package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.footer.FooterRequest;
import com.hitex.yousim.dto.response.footer.FooterResponse;
import com.hitex.yousim.model.Footer;
import com.hitex.yousim.repository.FooterRepository;
import com.hitex.yousim.service.FooterService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class FooterServiceImpl implements FooterService {
    @Autowired
    FooterRepository footerRepository;

    @Override
    public FooterResponse getFooterInfo(BaseRequestData request) throws ApplicationException {
        FooterResponse footerResponse = new FooterResponse();
        FooterRequest footerRequest = (FooterRequest) request.getWsRequest();
        try {
            Footer footer = footerRepository.getFooterById(footerRequest.getFooterId());
            BeanUtils.copyProperties(footer, footerResponse);
        } catch (Exception e) {
            throw e;
        }
        return  footerResponse;
    }

    @Override
    public FooterResponse updateFooter(BaseRequestData request) throws ApplicationException {
        FooterResponse footerResponse = new FooterResponse();
        FooterRequest footerRequest = (FooterRequest) request.getWsRequest();
        try {
            Footer footer = footerRepository.getFooterById(footerRequest.getFooterId());
            if (ObjectUtils.isEmpty(footer)) {
                throw new ApplicationException("ERR_0000111");
            }

            footer.setHotline(footerRequest.getHotline());
            footer.setEmail(footerRequest.getEmail());
            footer.setStatus(footerRequest.isStatus());
            footerRepository.save(footer);
            BeanUtils.copyProperties(footer, footerResponse);
        } catch (Exception e) {
            throw new ApplicationException("ERR_0000107");
        }
        return footerResponse;
    }
}
