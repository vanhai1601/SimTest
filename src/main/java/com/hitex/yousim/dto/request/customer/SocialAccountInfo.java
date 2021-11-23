package com.hitex.yousim.dto.request.customer;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class SocialAccountInfo implements IRequestData {
    private String id;
    private String email;
    private String name;
    private String picture;
    private String provider;

    @Override
    public boolean isValid() {
        return false;
    }
}
