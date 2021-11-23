package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.service.ServiceRequest;
import com.hitex.yousim.dto.response.servicepage.ListServiceLanguageResponse;
import com.hitex.yousim.service.ServicePageLanguageService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
public class ServicePageController extends BaseController {

    @Autowired
    ServicePageLanguageService servicePageLanguageService;

    @PostMapping(value = "listServicePage")
    @ResponseBody
    public ResponseEntity<?> listServicePage(@RequestBody BaseRequestData<ServiceRequest> request) throws ApplicationException {
        try {
            ListServiceLanguageResponse listServiceResponse = servicePageLanguageService.getServiceLang(request);
            return success(listServiceResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }

    }


}
