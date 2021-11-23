package com.hitex.yousim.dto.request.news;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.News;
import lombok.Data;

@Data
public class NewsRequest extends News implements IRequestData {
   private String text;
   private String language;

    @Override
    public boolean isValid() {
        return false;
    }

}
