package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.reply.ReplyRequest;
import com.hitex.yousim.dto.request.stockModel.StockModelRequest;
import com.hitex.yousim.dto.response.reply.ListReplyReponse;
import com.hitex.yousim.dto.response.stockModel.ListStockModelRespone;
import com.hitex.yousim.service.StockModelService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api")
public class StockModelController extends BaseController{
    @Autowired
    private StockModelService stockModelService;

    @PostMapping("/getListStockModel")
    public ResponseEntity<?> getListStockModel(@RequestBody BaseRequestData<StockModelRequest> baseRequestData) throws ApplicationException {
        try {
            ListStockModelRespone listStockModelRespone = stockModelService.getListStockModel(baseRequestData);
            return success(listStockModelRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
