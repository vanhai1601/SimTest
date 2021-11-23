package com.hitex.yousim.dto.response.footer;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.FooterLanguage;
import lombok.Data;

@Data
public class FooterLanguageResponse extends FooterLanguage implements IResponseData {
    private String hotline;
    private String email;
}
