package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.footer.FooterLanguageRequest;
import com.hitex.yousim.dto.request.footer.FooterRequest;
import com.hitex.yousim.dto.response.footer.FooterLanguageResponse;
import com.hitex.yousim.service.FooterLanguageService;
import com.hitex.yousim.service.FooterService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/")
public class FooterController extends BaseController {
    @Autowired
    FooterService footerService;
    @Autowired
    FooterLanguageService footerLanguageService;

    @PostMapping("footer")
    @ResponseBody
    public ResponseEntity getFooter(@RequestBody BaseRequestData<FooterRequest> requestData) throws ApplicationException {
        try {
            FooterLanguageResponse footerResponse = footerLanguageService.getFooter(requestData);
            return success(footerResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("footerUpdate")
    @ResponseBody
    public ResponseEntity footerUpdate(@RequestBody BaseRequestData<FooterRequest> requestData) throws ApplicationException {
        try {
            FooterLanguageResponse footerResponse = footerLanguageService.updateFooter(requestData);
            return success(footerResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("addLanguage")
    @ResponseBody
    public ResponseEntity footerLanguage(@RequestBody BaseRequestData<FooterLanguageRequest> requestData) throws ApplicationException {
        try {
            FooterLanguageResponse footerResponseLang = footerLanguageService.addLanguage(requestData);
            return success(footerResponseLang);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
