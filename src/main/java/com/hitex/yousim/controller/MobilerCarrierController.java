package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.mobileCarrier.MobileCarrierRequest;
import com.hitex.yousim.dto.request.nation.NationRequest;
import com.hitex.yousim.dto.response.mobileCarrier.ListMobileCarrierResponse;
import com.hitex.yousim.dto.response.nation.ListNationResponse;
import com.hitex.yousim.service.MobileCarrierService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class MobilerCarrierController extends BaseController{
    @Autowired
    private MobileCarrierService mobileCarrierService;

    @PostMapping("/getListMobileCarrier")
    public ResponseEntity<?> getListMobileCarrier(@RequestBody BaseRequestData<MobileCarrierRequest> baseRequestData) throws ApplicationException {
        try {
            ListMobileCarrierResponse listMobileCarrierResponse = mobileCarrierService.getListMobileCarrierByNation(baseRequestData);
            return success(listMobileCarrierResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
