package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.mobileCarrier.MobileCarrierRequest;
import com.hitex.yousim.dto.response.mobileCarrier.ListMobileCarrierResponse;
import com.hitex.yousim.dto.response.mobileCarrier.MobileCarrierResponse;
import com.hitex.yousim.model.MobileCarrier;
import com.hitex.yousim.repository.MobileCarrierRepository;
import com.hitex.yousim.service.MobileCarrierService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileCarrierServiceImpl implements MobileCarrierService {
    @Autowired
    private MobileCarrierRepository mobileCarrierRepository;

    @Override
    public ListMobileCarrierResponse getListMobileCarrierByNation(BaseRequestData baseRequestData) throws ApplicationException {
        ListMobileCarrierResponse listMobileCarrierResponse = new ListMobileCarrierResponse();
        MobileCarrierRequest mobileCarrierRequest = (MobileCarrierRequest) baseRequestData.getWsRequest();
        List<MobileCarrierResponse> mobileCarrierResponseList = new ArrayList<>();
        try {
            List<MobileCarrier> mobileCarrierList = mobileCarrierRepository.findMobileCarrierByNationId(mobileCarrierRequest.getNationId());
            if(mobileCarrierList.size() == 0) {
                throw new ApplicationException("ERR_0000301");
            }
            for(MobileCarrier mobileCarrier : mobileCarrierList){
                MobileCarrierResponse mobileCarrierResponse = new MobileCarrierResponse();
                BeanUtils.copyProperties(mobileCarrier,mobileCarrierResponse);
                mobileCarrierResponseList.add(mobileCarrierResponse);
            }
            listMobileCarrierResponse.setMobileCarrierResponseList(mobileCarrierResponseList);
            return listMobileCarrierResponse;
        } catch (ApplicationException e){
            throw e;
        }
    }
}
