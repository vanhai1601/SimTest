package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.firstNumberSim.FirstNumberSimRequest;
import com.hitex.yousim.dto.response.firstNumberSim.ListFirstNumberSimResponse;
import com.hitex.yousim.service.FirstNumberSimService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class FirstNumberSimController extends BaseController {
    @Autowired
    private FirstNumberSimService firstNumberSimService;

    @PostMapping("/getListFirstNumberSim")
    public ResponseEntity<?> getListFirstNumberSim(@RequestBody BaseRequestData<FirstNumberSimRequest> baseRequestData) throws ApplicationException {
        try {
            ListFirstNumberSimResponse listFirstNumberSimResponse = firstNumberSimService.getfirstNumberSimByMobileCarrier(baseRequestData);
            return success(listFirstNumberSimResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
