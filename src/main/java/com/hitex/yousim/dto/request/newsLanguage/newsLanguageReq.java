package com.hitex.yousim.dto.request.newsLanguage;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.News;
import com.hitex.yousim.model.view.vNewsLanguage;
import lombok.Data;

@Data
public class newsLanguageReq extends vNewsLanguage implements IRequestData {
    private int page;
    private int pageSize;

    @Override
    public boolean isValid() {
        return false;
    }
}
