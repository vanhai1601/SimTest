package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.kitAndIsdn.KitAndIsdnRequest;
import com.hitex.yousim.dto.response.kitAndIsdn.KitAndIsdnResponse;
import com.hitex.yousim.dto.response.kitAndIsdn.ListKitAndIsdnResponse;;
import com.hitex.yousim.service.KitAndIsdnService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class KitAndIsdnController extends BaseController {
    @Autowired
    private KitAndIsdnService kitAndIsdnService;

    //Bộ lọc sim
    @PostMapping("/seacrKidAndIsdn")
    public ResponseEntity<?> getListKitAndIsdn(@RequestBody BaseRequestData<KitAndIsdnRequest> baseRequestData) throws ApplicationException {
        try {
            ListKitAndIsdnResponse listKitAndIsdnResponse = kitAndIsdnService.searchKitAndIsdn(baseRequestData);
            return success(listKitAndIsdnResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    // Lấy sim theo thẻ loại
    @PostMapping("/getKitAndIsdnByType")
    public ResponseEntity<?> getListKitAndIsdnByType(@RequestBody BaseRequestData<KitAndIsdnRequest> baseRequestData) throws ApplicationException {
        try {
            ListKitAndIsdnResponse listKitAndIsdnResponse = kitAndIsdnService.getListKitAndIsdnByStockModelId(baseRequestData);
            return success(listKitAndIsdnResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    // Tạo khuyến mại ramdom
    @PostMapping("/setPromotionRandom")
    public ResponseEntity<?> setPromotionRandom(@RequestBody BaseRequestData<KitAndIsdnRequest> baseRequestData) throws ApplicationException {
        try {
            ListKitAndIsdnResponse listKitAndIsdnResponse = kitAndIsdnService.setPromotionRandom(baseRequestData);
            return success(listKitAndIsdnResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    // Lấy danh sách sim khuyến mại DESC và thời gian mới nhất
    @PostMapping("/getKitAndIsdnByPromotion")
    public ResponseEntity<?> getKitAndIsdnByPromotion(@RequestBody BaseRequestData<KitAndIsdnRequest> baseRequestData) throws ApplicationException {
        try {
            ListKitAndIsdnResponse listKitAndIsdnResponse = kitAndIsdnService.getKitAndIsdnByPromotion(baseRequestData);
            return success(listKitAndIsdnResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    // Chi tiết Sim
    @PostMapping("/getKitAndIsdnDetail")
    public ResponseEntity getKitAndIsdnDetail(@RequestBody BaseRequestData<KitAndIsdnRequest> baseRequestData) throws ApplicationException {
        try {
            KitAndIsdnResponse kitAndIsdnResponse = kitAndIsdnService.getKitAndIsdnDetail(baseRequestData);
            return success(kitAndIsdnResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
