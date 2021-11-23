package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.kitAndIsdn.KitAndIsdnResponse;
import com.hitex.yousim.dto.response.kitAndIsdn.ListKitAndIsdnResponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface KitAndIsdnService {
    ListKitAndIsdnResponse searchKitAndIsdn(BaseRequestData baseRequestData) throws ApplicationException;

    ListKitAndIsdnResponse getListKitAndIsdnByStockModelId(BaseRequestData baseRequestData) throws ApplicationException;

    ListKitAndIsdnResponse setPromotionRandom(BaseRequestData baseRequestData) throws  ApplicationException;

    ListKitAndIsdnResponse getKitAndIsdnByPromotion(BaseRequestData baseRequestData) throws ApplicationException;

    KitAndIsdnResponse getKitAndIsdnDetail(BaseRequestData baseRequestData) throws ApplicationException;
}
