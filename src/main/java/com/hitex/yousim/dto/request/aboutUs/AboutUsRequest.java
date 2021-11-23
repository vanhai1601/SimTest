package com.hitex.yousim.dto.request.aboutUs;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.AboutUs;
import lombok.Data;

@Data
public class AboutUsRequest extends AboutUs implements IRequestData {

    private int type;
    private String image;
    private String partner;

    @Override
    public boolean isValid() {
        return false;
    }
}
