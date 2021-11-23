package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.servicepage.ListServiceLanguageResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface ServicePageLanguageService {
    ListServiceLanguageResponse getServiceLang(BaseRequestData request) throws ApplicationException;
}
