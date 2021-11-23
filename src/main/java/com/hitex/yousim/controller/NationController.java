package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.nation.NationRequest;
import com.hitex.yousim.dto.response.nation.ListNationResponse;
import com.hitex.yousim.service.NationService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class NationController extends BaseController {
    @Autowired
    private NationService nationService;

    @PostMapping("/getListNation")
    public ResponseEntity<?> getListNation(@RequestBody BaseRequestData<NationRequest> baseRequestData) throws ApplicationException {
        try {
            ListNationResponse listNationResponse = nationService.getListNation(baseRequestData);
            return success(listNationResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
