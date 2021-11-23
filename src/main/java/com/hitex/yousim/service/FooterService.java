package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.footer.FooterResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface FooterService {
    FooterResponse getFooterInfo(BaseRequestData footerRequest) throws ApplicationException;
    FooterResponse updateFooter(BaseRequestData footerRequest) throws ApplicationException;
}
