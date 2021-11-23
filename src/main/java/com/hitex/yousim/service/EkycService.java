package com.hitex.yousim.service;

import com.hitex.yousim.dto.response.customer.CustomerResponse;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

public interface EkycService {
    CustomerResponse getInfoCardFont(MultipartFile file, String type, String typeDoc) throws ApplicationException;

    CustomerResponse getInfoImgBack(MultipartFile file, String type, String typeDoc) throws ApplicationException, IOException, ParseException;

    int getInfoAvatar(MultipartFile file, String imgFont) throws IOException, ApplicationException;
}
