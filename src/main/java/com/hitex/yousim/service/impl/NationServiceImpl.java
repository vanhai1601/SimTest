package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.nation.NationRequest;
import com.hitex.yousim.dto.response.nation.ListNationResponse;
import com.hitex.yousim.dto.response.nation.NationResponse;
import com.hitex.yousim.model.Nation;
import com.hitex.yousim.repository.NationRepository;
import com.hitex.yousim.service.NationService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NationServiceImpl implements NationService {
    @Autowired
    private NationRepository nationRepository;

    @Override
    public ListNationResponse getListNation(BaseRequestData baseRequestData) throws ApplicationException {
        ListNationResponse listNationResponse = new ListNationResponse();
        List<NationResponse> nationResponseList = new ArrayList<>();
        try {
            List<Nation> nationList = nationRepository.findAll();
            for(Nation nation:nationList){
                NationResponse nationResponse = new NationResponse();
                BeanUtils.copyProperties(nation,nationResponse);
                nationResponseList.add(nationResponse);
            }
            listNationResponse.setNationResponseList(nationResponseList);
            return listNationResponse;
        } catch (Exception e){
            throw new ApplicationException("error");
        }
    }
}
