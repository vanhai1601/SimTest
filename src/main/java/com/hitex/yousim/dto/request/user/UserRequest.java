package com.hitex.yousim.dto.request.user;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.User;
import lombok.Data;

@Data
public class UserRequest extends User implements IRequestData {
    private String newPass;
    private String passRetype;
    private int pageSize;
    private int page;
    @Override
    public boolean isValid() {
        return false;
    }
}
