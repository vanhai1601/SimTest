package com.hitex.yousim.dto.response.servicepage;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.ServicePageLanguage;
import lombok.Data;

@Data
public class ServiceLanguageResponse extends ServicePageLanguage implements IResponseData {
    private String icon;
    private String image;

}
