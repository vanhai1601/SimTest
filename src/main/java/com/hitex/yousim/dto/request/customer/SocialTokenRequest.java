package com.hitex.yousim.dto.request.customer;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class SocialTokenRequest implements IRequestData {
    private String accessToken;
    private String provider;
    @Override
    public boolean isValid() {
        return false;
    }
}
