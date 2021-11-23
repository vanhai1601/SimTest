package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.contact.ContactRequest;
import com.hitex.yousim.dto.response.contact.ContactReponse;
import com.hitex.yousim.service.ContactService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/api")
public class ContactController extends BaseController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/addContact")
    public ResponseEntity addContact(@RequestBody BaseRequestData<ContactRequest> baseRequestData) throws ApplicationException {
        try {
            ContactReponse feedbackReponse = contactService.addContact(baseRequestData);
            return success(feedbackReponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
