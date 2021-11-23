package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.firstNumberSim.FirstNumberSimRequest;
import com.hitex.yousim.dto.response.firstNumberSim.FirstNumberSimResponse;
import com.hitex.yousim.dto.response.firstNumberSim.ListFirstNumberSimResponse;
import com.hitex.yousim.model.FirstNumberSim;
import com.hitex.yousim.repository.FirstNumberSimRepository;
import com.hitex.yousim.service.FirstNumberSimService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirstNumberSimServiceImpl implements FirstNumberSimService {
    @Autowired
    private FirstNumberSimRepository firstNumberSimRepository;

    @Override
    public ListFirstNumberSimResponse getfirstNumberSimByMobileCarrier(BaseRequestData baseRequestData) throws ApplicationException {
        ListFirstNumberSimResponse listFirstNumberSimResponse = new ListFirstNumberSimResponse();
        List<FirstNumberSimResponse> firstNumberSimResponseList = new ArrayList<>();
        FirstNumberSimRequest firstNumberSimRequest = (FirstNumberSimRequest) baseRequestData.getWsRequest();
        try {
            List<FirstNumberSim> firstNumberSimList = firstNumberSimRepository.findFirstNumberSimByMobileCarrier(firstNumberSimRequest.getMobileCarrierId());
             if(firstNumberSimList.size() == 0){
                throw new ApplicationException("ERR_0000301");
            }
             for(FirstNumberSim firstNumberSim: firstNumberSimList) {
                 FirstNumberSimResponse firstNumberSimResponse = new FirstNumberSimResponse();
                 BeanUtils.copyProperties(firstNumberSim, firstNumberSimResponse);
                 firstNumberSimResponseList.add(firstNumberSimResponse);
             }
            listFirstNumberSimResponse.setFirstNumberSimResponseList(firstNumberSimResponseList);
            return listFirstNumberSimResponse;
        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e){
            throw new ApplicationException("error");
        }
    }
}
