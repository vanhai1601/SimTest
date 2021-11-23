package com.hitex.yousim.dto.request.contact;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Contact;

public class ContactRequest extends Contact implements IRequestData {
    @Override
    public boolean isValid() {
        return false;
    }
}
