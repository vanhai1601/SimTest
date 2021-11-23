package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.physicType.ListPhysicTypeResponse;
import com.hitex.yousim.utils.exception.ApplicationException;;


public interface PhysicTypeService {
    ListPhysicTypeResponse getListPhysictype(BaseRequestData baseRequestData)throws ApplicationException;
}
