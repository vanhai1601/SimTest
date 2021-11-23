package com.hitex.yousim.service.impl;

import com.hitex.yousim.constant.Constant;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.aboutUs.AboutUsAndLanguageRequest;
import com.hitex.yousim.dto.request.aboutUs.AboutUsLanguageRequest;
import com.hitex.yousim.dto.request.aboutUs.AboutUsRequest;
import com.hitex.yousim.dto.request.aboutUs.ViewAboutUsLanguageRequest;
import com.hitex.yousim.dto.request.kitAndIsdn.KitAndIsdnRequest;
import com.hitex.yousim.dto.response.aboutUs.*;
import com.hitex.yousim.model.AboutUs;
import com.hitex.yousim.model.AboutUsLanguage;
import com.hitex.yousim.model.StockModel;
import com.hitex.yousim.model.view.ViewAboutUsLanguage;
import com.hitex.yousim.repository.AboutUsAndLanguageRepository;
import com.hitex.yousim.repository.AboutUsLanguageRepository;
import com.hitex.yousim.repository.AboutUsRepository;
import com.hitex.yousim.service.AboutUsService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
@Log4j2
public class AboutUsServiceImpl implements AboutUsService {
    @Autowired
    AboutUsAndLanguageRepository aboutUsAndLanguageRepository;
    @Autowired
    AboutUsRepository aboutUsRepository;
    @Autowired
    AboutUsLanguageRepository aboutUsLanguageRepository;

    //lay danh sach thong tin ve chung toi
    @Override
    public ListAboutUsLanguageResponse listAboutUs(BaseRequestData requestData) throws ApplicationException {
        ListAboutUsLanguageResponse listAboutUsLanguageResponse = new ListAboutUsLanguageResponse();
        AboutUsAndLanguageRequest aboutUsRequest = (AboutUsAndLanguageRequest) requestData.getWsRequest();
        List<AboutUsAndLanguageResponse> aboutUsLanguageAndResponse = new ArrayList<>();
        //chon type de lay danh sach, neu khong chon type -> lay tat ca ban ghi ve chung toi
        try {
            if (aboutUsRequest.getType() == Constant.ABOUT_LIST_TYPE || aboutUsRequest.equals(null)) {
                List<ViewAboutUsLanguage> viewAboutUsLanguages = aboutUsAndLanguageRepository.findAllList(String.valueOf(LocaleContextHolder.getLocale()));
                for (ViewAboutUsLanguage viewAboutUsLanguage : viewAboutUsLanguages) {
                    AboutUsAndLanguageResponse aboutUsAndLanguageResponse = new AboutUsAndLanguageResponse();
                    BeanUtils.copyProperties(viewAboutUsLanguage, aboutUsAndLanguageResponse);
                    aboutUsLanguageAndResponse.add(aboutUsAndLanguageResponse);
                }
                listAboutUsLanguageResponse.setAboutUsAndLanguageResponseList(aboutUsLanguageAndResponse);
            }
            if (aboutUsRequest.getType() == Constant.ABOUT_TYPE_ONE || aboutUsRequest.getType() == Constant.ABOUT_TYPE_TWO || aboutUsRequest.getType() == Constant.ABOUT_TYPE_THREE){
                List<ViewAboutUsLanguage> aboutUsListType = aboutUsAndLanguageRepository.getAboutUsByType(aboutUsRequest.getType(), String.valueOf(LocaleContextHolder.getLocale()));
                List<AboutUsAndLanguageResponse> aboutUsResponseList = new ArrayList<>();
                for (ViewAboutUsLanguage aboutUs : aboutUsListType) {
                    AboutUsAndLanguageResponse aboutUsResponse = new AboutUsAndLanguageResponse();
                    BeanUtils.copyProperties(aboutUs, aboutUsResponse);
                    aboutUsResponseList.add(aboutUsResponse);
                }
                listAboutUsLanguageResponse.setAboutUsAndLanguageResponseList(aboutUsResponseList);
            }
            else{
                throw new ApplicationException("ERR_0000504");
            }
        }
        catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }
        return listAboutUsLanguageResponse;
    }

    //lay danh sach doi tac
    @Override
    public ListAboutUsResponse listPartner(BaseRequestData requestData) throws ApplicationException {
        ListAboutUsResponse listAboutUsResponse = new ListAboutUsResponse();
        AboutUsRequest aboutUsRequest = (AboutUsRequest) requestData.getWsRequest();
        List<AboutUsResponse> aboutUsResponses = new ArrayList<>();
        List<AboutUs> aboutUsListType = aboutUsRepository.findPartnerByType();
        if (aboutUsRequest.getType() == 0 || aboutUsRequest.equals(null)) {
            for (AboutUs aboutUs : aboutUsListType) {
                AboutUsResponse aboutUsResponse = new AboutUsResponse();
                BeanUtils.copyProperties(aboutUs, aboutUsResponse);
                aboutUsResponses.add(aboutUsResponse);
            }
            listAboutUsResponse.setAboutUsResponseList(aboutUsResponses);
        } else {
            List<AboutUsResponse> aboutUsResponseList = new ArrayList<>();
            for (AboutUs aboutUs : aboutUsListType) {
                AboutUsResponse aboutUsResponse = new AboutUsResponse();
                BeanUtils.copyProperties(aboutUs, aboutUsResponse);
                aboutUsResponseList.add(aboutUsResponse);
            }
            listAboutUsResponse.setAboutUsResponseList(aboutUsResponseList);
        }
        return listAboutUsResponse;
    }
    //sua thong tin doi tac
    @Override
    public ListAboutUsAndLanguageResponse updateAboutUs(BaseRequestData requestData) throws ApplicationException {
        ListAboutUsAndLanguageResponse listAboutUsAndLanguageResponse = new ListAboutUsAndLanguageResponse();
        AboutUsAndLanguageRequest aboutUsAndLanguageRequest = (AboutUsAndLanguageRequest) requestData.getWsRequest();
        AboutUs aboutUs = aboutUsRepository.findAboutUsById(aboutUsAndLanguageRequest.getId());
        try {
            if (ObjectUtils.isEmpty(aboutUsAndLanguageRequest.getType())) {
                throw new ApplicationException("ERR_0000504");
            }
            AboutUsLanguage aboutUsLanguage = aboutUsLanguageRepository.findAboutUsLanguageByIdAboutUs(aboutUs.getId(), String.valueOf(LocaleContextHolder.getLocale()));

            if (!ObjectUtils.isEmpty(aboutUsLanguage)) {
                AboutUsLanguageResponse aboutUsLanguageResponse = new AboutUsLanguageResponse();
                AboutUsResponse aboutUsResponse = new AboutUsResponse();
                aboutUsLanguage.setContent(aboutUsAndLanguageRequest.getContent());
                aboutUsLanguage.setTitle(aboutUsAndLanguageRequest.getTitle());
                BeanUtils.copyProperties(aboutUsLanguage, aboutUsLanguageResponse);
                aboutUs.setImage(aboutUsAndLanguageRequest.getImage());
                BeanUtils.copyProperties(aboutUs, aboutUsResponse);
                aboutUsRepository.save(aboutUs);
                aboutUsLanguageRepository.save(aboutUsLanguage);
            } else {
                throw new ApplicationException("ERR_0000503");
            }
        } catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }
        return listAboutUsAndLanguageResponse;
    }

    //them moi doi tac doi tac
    @Override
    public AboutUsResponse addPartner(BaseRequestData requestData) throws ApplicationException {
        AboutUsResponse aboutUsResponse = new AboutUsResponse();
        AboutUsRequest aboutUsRequest = (AboutUsRequest) requestData.getWsRequest();
        try {
            AboutUs partnerTitle = aboutUsRepository.findPartnerByName(aboutUsRequest.getPartner());
            if (ObjectUtils.isEmpty(partnerTitle)) {
                AboutUs aboutUs = new AboutUs();
                BeanUtils.copyProperties(aboutUsRequest, aboutUs);
                aboutUsRepository.save(aboutUs);
                BeanUtils.copyProperties(aboutUs, aboutUsResponse);
            } else {
                throw new ApplicationException("ERR_0000005", MessageUtils.getMessage("Partner"));
            }
        } catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }
        return aboutUsResponse;
    }
    //sua thong tin doi tac
    @Override
    public AboutUsResponse updatePartner(BaseRequestData requestData) throws ApplicationException {
        AboutUsResponse aboutUsResponse = new AboutUsResponse();
        AboutUsRequest aboutUsRequest = (AboutUsRequest) requestData.getWsRequest();
        try {
            if (ObjectUtils.isEmpty(aboutUsRequest.getPartner())) {
                throw new ApplicationException("ERR_0000502");
            }
            AboutUs checkPartner = aboutUsRepository.findPartnerByName(aboutUsRequest.getPartner());
            if (!ObjectUtils.isEmpty(checkPartner)) {
                AboutUs partnerUpdate = aboutUsRepository.findPartnerByName(aboutUsRequest.getPartner());
                partnerUpdate.setImage(aboutUsRequest.getImage());
                partnerUpdate.setPartner(aboutUsRequest.getPartner());
                aboutUsRepository.save(partnerUpdate);
                BeanUtils.copyProperties(partnerUpdate, aboutUsResponse);
            } else {
                throw new ApplicationException("ERR_0000502");
            }
        } catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }
        return aboutUsResponse;
    }

    //xoa doi tac
    @Override
    public AboutUsResponse deletePartner(BaseRequestData requestData) throws ApplicationException {
        AboutUsRequest aboutUsRequest = (AboutUsRequest) requestData.getWsRequest();
        try {
            if (ObjectUtils.isEmpty(aboutUsRequest.getPartner())) {
                throw new ApplicationException("ERR_0000502");
            }
            AboutUs aboutUs = aboutUsRepository.findPartnerByName(aboutUsRequest.getPartner());
            if (!ObjectUtils.isEmpty(aboutUs)) {
                AboutUs partner = aboutUsRepository.findPartnerByName(aboutUsRequest.getPartner());
                aboutUsRepository.delete(partner);
            } else {
                throw new ApplicationException("ERR_0000502");
            }
        } catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }
        return null;
    }
}
