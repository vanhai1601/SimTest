package com.hitex.yousim.service.impl;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.contact.ContactRequest;
import com.hitex.yousim.dto.response.contact.ContactReponse;
import com.hitex.yousim.model.Contact;
import com.hitex.yousim.model.User;
import com.hitex.yousim.repository.ContactRepository;
import com.hitex.yousim.repository.UserRepository;
import com.hitex.yousim.service.ContactService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ContactReponse addContact(BaseRequestData baseRequestData) throws ApplicationException {
        ContactReponse contactReponse = new ContactReponse();
        ContactRequest contactRequest =(ContactRequest) baseRequestData.getWsRequest();
        try {
            User userLogin = userRepository.findUserBySessionAngToken(baseRequestData.getSessionId(),baseRequestData.getToken());
            if(ObjectUtils.isEmpty(userLogin)){
                throw new ApplicationException("ERR_0000003");
            }
            Contact contact = new Contact();

            if(StringUtils.isEmpty(contactRequest.getUserName())){
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("user"));
            }
            contact.setUserName(contactRequest.getUserName());

            if(StringUtils.isEmpty(contactRequest.getEmail())){
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("email"));
            }
            contact.setEmail(contactRequest.getEmail());

            if(StringUtils.isEmpty(contactRequest.getPhoneNumber())){
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("phoneNumber"));
            }
            contact.setPhoneNumber(contactRequest.getPhoneNumber());
            if(StringUtils.isEmpty(contactRequest.getContent())){
                throw new ApplicationException("ERR_0000004", MessageUtils.getMessage("content"));
            }
            contact.setContent(contactRequest.getContent());
            contact.setStatus(1);
            contact.setCreateTime(LocalDateTime.now());
            contactRepository.save(contact);
            BeanUtils.copyProperties(contact, contactReponse);

        } catch (ApplicationException e) {
            e.getLocalizedMessage();
            throw e;
        }

        return contactReponse;
    }
}
