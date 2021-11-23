package com.hitex.yousim.dto.request.aboutUs;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.AboutUsLanguage;
import lombok.Data;

@Data
public class AboutUsLanguageRequest extends AboutUsLanguage implements IRequestData {
    private String content;
    private String title;
    private String language;
    private int idAboutUs;

    private String image;
    @Override
    public boolean isValid() {
        return false;
    }
}
