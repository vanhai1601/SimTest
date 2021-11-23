package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.orderProduct.ListOrderProductRequest;
import com.hitex.yousim.dto.response.orderProduct.OrderProductResponse;
import com.hitex.yousim.service.OrderService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class OrderController extends BaseController {
    @Autowired
    OrderService orderService;

    @PostMapping("addOrder")
    @ResponseBody
    public ResponseEntity addOrder(@RequestBody BaseRequestData<ListOrderProductRequest> baseRequestData) throws ApplicationException {
        try {
            OrderProductResponse orderRespone = orderService.addOrder(baseRequestData);
            return success(orderRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
