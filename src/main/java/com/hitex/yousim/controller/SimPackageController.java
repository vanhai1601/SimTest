package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.simPackage.SimPackageRequest;
import com.hitex.yousim.dto.response.simPackage.ListSimPackageResponse;
import com.hitex.yousim.dto.response.simPackage.SimPackageResponse;
import com.hitex.yousim.service.SimPackageService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class SimPackageController extends BaseController {
    @Autowired
    private SimPackageService simPackageService;

    // Lấy gói cước theo khuyến mại và thời gian mới nhất
    @PostMapping("/getSimPackagePromotion")
    public ResponseEntity<?> getSimPackagePromotion(@RequestBody BaseRequestData<SimPackageRequest> baseRequestData) throws ApplicationException {
        try {
            ListSimPackageResponse listSimPackageResponse = simPackageService.getSimPackagePromotion(baseRequestData);
            return success(listSimPackageResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
    // Bộ lọc gói cước
    @PostMapping("/searchSimPackage")
    public ResponseEntity<?> searchAndSortSimPackage(@RequestBody BaseRequestData<SimPackageRequest> baseRequestData) throws ApplicationException {
        try {
            ListSimPackageResponse listSimPackageResponse = simPackageService.searchAndSortSimpackage(baseRequestData);
            return success(listSimPackageResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
    //Lấy danh sách gói cước theo nhà mạng
    @PostMapping("/getListSimPackageByMobileCarrier")
    public ResponseEntity<?> getListSimPackageByMobileCarrier(@RequestBody BaseRequestData<SimPackageRequest> baseRequestData) throws ApplicationException {
        try {
            ListSimPackageResponse listSimPackageResponse = simPackageService.getListSimPackageByMobileCarrier(baseRequestData);
            return success(listSimPackageResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
    // Chi tiết gói cước
    @PostMapping("/getSimPackageDetail")
    public ResponseEntity getSimPackageDetail(@RequestBody BaseRequestData<SimPackageRequest> baseRequestData) throws ApplicationException {
        try {
            SimPackageResponse simPackageResponse = simPackageService.getListSimPackageById(baseRequestData);
            return success(simPackageResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
