package com.hitex.yousim.dto.request.service;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.ServicePageLanguage;
import lombok.Data;

@Data
public class ServiceLanguageRequest extends ServicePageLanguage implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
