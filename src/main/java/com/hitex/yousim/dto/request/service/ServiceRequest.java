package com.hitex.yousim.dto.request.service;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.ServicePage;

public class ServiceRequest extends ServicePage implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
