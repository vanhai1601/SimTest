package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.nation.ListNationResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface NationService {
    ListNationResponse getListNation(BaseRequestData baseRequestData) throws ApplicationException;
}
