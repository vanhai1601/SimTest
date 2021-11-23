package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.footer.FooterLanguageResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface FooterLanguageService {
    FooterLanguageResponse getFooter(BaseRequestData footerRequest) throws ApplicationException;
    FooterLanguageResponse addLanguage(BaseRequestData footerRequest) throws ApplicationException;
    FooterLanguageResponse updateFooter(BaseRequestData footerRequest) throws ApplicationException;


}
