package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.physicType.ListPhysicTypeResponse;
import com.hitex.yousim.dto.response.physicType.PhysicTypeResponse;
import com.hitex.yousim.model.PhysicType;
import com.hitex.yousim.repository.PhysicTypeRepository;
import com.hitex.yousim.service.PhysicTypeService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhysicTypeServiceImpl implements PhysicTypeService {
    @Autowired
    private PhysicTypeRepository physicTypeRepository;
    @Override
    public ListPhysicTypeResponse getListPhysictype(BaseRequestData baseRequestData) throws ApplicationException {
        ListPhysicTypeResponse listPhysicTypeResponse = new ListPhysicTypeResponse();
        try {
            List<PhysicType> physicTypeList = physicTypeRepository.findAll(String.valueOf(LocaleContextHolder.getLocale()));
            List<PhysicTypeResponse> physicTypeResponseList = new ArrayList<>();
            for(PhysicType physicType: physicTypeList) {
                PhysicTypeResponse physicTypeResponse = new PhysicTypeResponse();
                BeanUtils.copyProperties(physicType,physicTypeResponse);
                physicTypeResponseList.add(physicTypeResponse);
            }
            listPhysicTypeResponse.setPhysicTypeResponseList(physicTypeResponseList);
            return listPhysicTypeResponse;
        } catch (Exception e) {
            throw new ApplicationException("error");
        }
    }
}
