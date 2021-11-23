package com.hitex.yousim.dto.request.footer;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Footer;
import lombok.Data;

@Data
public class FooterRequest extends Footer implements IRequestData {

    @Override
    public boolean isValid() {
        return false;
    }
}
