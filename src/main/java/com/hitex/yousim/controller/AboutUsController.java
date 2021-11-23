package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.aboutUs.AboutUsAndLanguageRequest;
import com.hitex.yousim.dto.request.aboutUs.AboutUsRequest;
import com.hitex.yousim.dto.request.aboutUs.ViewAboutUsLanguageRequest;
import com.hitex.yousim.dto.response.aboutUs.*;
import com.hitex.yousim.repository.AboutUsAndLanguageRepository;
import com.hitex.yousim.service.AboutUsService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/")
public class AboutUsController extends BaseController {
    @Autowired
    AboutUsService aboutUsService;
    @Autowired
    AboutUsAndLanguageRepository aboutUsAndLanguageRepository;

    @PostMapping("/aboutUs")
    public ResponseEntity<?> listAboutUs(@RequestBody BaseRequestData<AboutUsAndLanguageRequest> baseRequestData) throws ApplicationException {
        try {
            ListAboutUsLanguageResponse aboutUsLanguageResponse = aboutUsService.listAboutUs(baseRequestData);
            return success(aboutUsLanguageResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/updateAboutUs")
    public ResponseEntity updateAboutUs(@RequestBody BaseRequestData<AboutUsAndLanguageRequest> requestData) throws ApplicationException {
        try {
            ListAboutUsAndLanguageResponse listAboutUsAndLanguageResponse = aboutUsService.updateAboutUs(requestData);
            return success(listAboutUsAndLanguageResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/listPartner")
    public ResponseEntity listPartner(@RequestBody BaseRequestData<AboutUsRequest> requestData) throws ApplicationException {
        try {
            ListAboutUsResponse aboutUsResponse = aboutUsService.listPartner(requestData);
            return success(aboutUsResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/addPartner")
    public ResponseEntity addPartner(@RequestBody BaseRequestData<AboutUsRequest> requestData) throws ApplicationException {
        try {
            AboutUsResponse aboutUsResponse = aboutUsService.addPartner(requestData);
            return success(aboutUsResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/updatePartner")
    public ResponseEntity updatePartner(@RequestBody BaseRequestData<AboutUsRequest> requestData) throws ApplicationException {
        try {
            AboutUsResponse aboutUsResponse = aboutUsService.updatePartner(requestData);
            return success(aboutUsResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/deletePartner")
    public ResponseEntity deletePartner(@RequestBody BaseRequestData<AboutUsRequest> requestData) throws ApplicationException {
        try {
            AboutUsResponse aboutUsResponse = aboutUsService.deletePartner(requestData);
            return success(aboutUsResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
