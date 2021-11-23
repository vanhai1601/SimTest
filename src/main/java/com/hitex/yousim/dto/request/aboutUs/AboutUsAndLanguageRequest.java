package com.hitex.yousim.dto.request.aboutUs;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.view.ViewAboutUsLanguage;
import lombok.Data;

@Data
public class AboutUsAndLanguageRequest extends ViewAboutUsLanguage implements IRequestData {
    private int type;
    private int id;
    private String image;
    private String content;
    private String title;
    private String language;
    @Override
    public boolean isValid() {
        return false;
    }
}
