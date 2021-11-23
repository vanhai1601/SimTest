package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.mobileCarrier.ListMobileCarrierResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface MobileCarrierService {
    ListMobileCarrierResponse getListMobileCarrierByNation(BaseRequestData baseRequestData) throws ApplicationException;
}
