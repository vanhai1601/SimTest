package com.hitex.yousim.dto.request.footer;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Footer;
import com.hitex.yousim.model.FooterLanguage;
import lombok.Data;

@Data
public class FooterLanguageRequest extends FooterLanguage implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
