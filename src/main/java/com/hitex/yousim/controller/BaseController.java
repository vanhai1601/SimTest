package com.hitex.yousim.controller;

import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.utils.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    // thành công có respone trả về
    protected  <T extends IResponseData> ResponseEntity success(T respone) {
        BaseResponseData baseResponseData= new BaseResponseData();
        baseResponseData.setErrorCode("success");
        baseResponseData.setMessage(MessageUtils.getMessage("success"));
        baseResponseData.setWsResponse(respone);
        return new ResponseEntity(baseResponseData, HttpStatus.OK);
    }
    protected  <T extends IResponseData> ResponseEntity error(String code, String message) {
        BaseResponseData baseResponseData= new BaseResponseData();
        baseResponseData.setErrorCode(code);
        baseResponseData.setMessage(message);
        return new ResponseEntity(baseResponseData, HttpStatus.OK);
    }

    // thành công không có respone trả về
    protected  ResponseEntity success() {
        BaseResponseData baseResponseData= new BaseResponseData();
        baseResponseData.setErrorCode("success");
        baseResponseData.setMessage(MessageUtils.getMessage("success"));
        return new ResponseEntity(baseResponseData, HttpStatus.OK);
    }
}
