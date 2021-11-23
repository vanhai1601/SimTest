package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.firstNumberSim.ListFirstNumberSimResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface FirstNumberSimService {
    ListFirstNumberSimResponse getfirstNumberSimByMobileCarrier(BaseRequestData baseRequestData) throws ApplicationException;
}
