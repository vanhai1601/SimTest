package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.simPackage.ListSimPackageResponse;
import com.hitex.yousim.dto.response.simPackage.SimPackageResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface SimPackageService {
    ListSimPackageResponse getSimPackagePromotion(BaseRequestData baseRequestData) throws ApplicationException;
    ListSimPackageResponse searchAndSortSimpackage(BaseRequestData baseRequestData) throws ApplicationException;
    ListSimPackageResponse getListSimPackageByMobileCarrier(BaseRequestData baseRequestData) throws ApplicationException;
    SimPackageResponse getListSimPackageById(BaseRequestData baseRequestData) throws ApplicationException;
}
