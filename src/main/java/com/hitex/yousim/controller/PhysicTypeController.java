package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.physicType.PhysicTypeRequest;
import com.hitex.yousim.dto.request.question.QuestionRequest;
import com.hitex.yousim.dto.response.physicType.ListPhysicTypeResponse;
import com.hitex.yousim.dto.response.question.QuestionReponse;
import com.hitex.yousim.service.PhysicTypeService;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PhysicTypeController extends BaseController {
    @Autowired
    private PhysicTypeService physicTypeService;

    @PostMapping("/getListPhysicType")
    public ResponseEntity<?> getListPhysicType(@RequestBody BaseRequestData<PhysicTypeRequest> baseRequestData) throws ApplicationException {
        try {
            ListPhysicTypeResponse listPhysicTypeResponse = physicTypeService.getListPhysictype(baseRequestData);
            return success(listPhysicTypeResponse);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }
}
