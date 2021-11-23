package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.aboutUs.*;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface AboutUsService {
    ListAboutUsLanguageResponse listAboutUs(BaseRequestData baseRequestData) throws ApplicationException;

    ListAboutUsAndLanguageResponse updateAboutUs(BaseRequestData requestData) throws ApplicationException;

    ListAboutUsResponse listPartner(BaseRequestData requestData) throws ApplicationException;

    AboutUsResponse addPartner(BaseRequestData requestData) throws ApplicationException;

    AboutUsResponse updatePartner(BaseRequestData requestData) throws ApplicationException;

    AboutUsResponse deletePartner(BaseRequestData requestData) throws ApplicationException;
}
