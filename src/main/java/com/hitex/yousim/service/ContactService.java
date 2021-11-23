package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.contact.ContactReponse;
import com.hitex.yousim.utils.exception.ApplicationException;

public interface ContactService {
    ContactReponse addContact(BaseRequestData baseRequestData) throws ApplicationException;
}
