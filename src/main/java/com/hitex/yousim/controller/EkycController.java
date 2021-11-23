package com.hitex.yousim.controller;

import com.hitex.yousim.constant.ApplicationCode;
import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.dto.response.customer.CustomerResponse;
import com.hitex.yousim.service.EkycService;
import com.hitex.yousim.utils.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/")
@Log4j2
public class EkycController {
    @Autowired
    EkycService ekycService;
    @PostMapping("/getInfoCardFont")
    @ResponseBody
    public ResponseEntity<?> getInfoCardFont(@RequestParam("file")MultipartFile file, @RequestParam("type") String type, @RequestParam("typeDoc") String typeDoc){
        BaseResponseData baseResponseData = new BaseResponseData();
        try {
            CustomerResponse customerResponse = ekycService.getInfoCardFont(file, type, typeDoc);
            baseResponseData.setErrorCode(String.valueOf(ApplicationCode.SUCCESS));
            baseResponseData.setMessage(ApplicationCode.getMessage(ApplicationCode.SUCCESS));
            baseResponseData.setWsResponse(customerResponse);

        } catch (ApplicationException e) {
            baseResponseData.setErrorCode(e.getCode());
            baseResponseData.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(baseResponseData, HttpStatus.OK);
    }
    @PostMapping(value = "getInfoCardBack")
    @ResponseBody
    public ResponseEntity<?> getInfoIdCardBack(@RequestParam("file") MultipartFile file, @RequestParam("type") String type,@RequestParam("typeDoc") String typeDoc) throws ApplicationException, IOException, ParseException {
        BaseResponseData responseData = new BaseResponseData();
        try {
            CustomerResponse customerResponse =  ekycService.getInfoImgBack(file,type,typeDoc);
            responseData.setErrorCode(String.valueOf(ApplicationCode.SUCCESS));
            responseData.setMessage(ApplicationCode.getMessage(ApplicationCode.SUCCESS));
            responseData.setWsResponse(customerResponse);
        }catch (ApplicationException e){
            responseData.setErrorCode(e.getCode());
            responseData.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping(value = "compareFaceImage")
    @ResponseBody
    public ResponseEntity<?> compareFaceImage(@RequestParam("file") MultipartFile file, @RequestParam("hashImgFont") String imgFont) throws ApplicationException, IOException, ParseException {
        BaseResponseData responseData = new BaseResponseData();
        try {
            ekycService.getInfoAvatar(file,imgFont);
            responseData.setErrorCode(String.valueOf(ApplicationCode.SUCCESS));
            responseData.setMessage(ApplicationCode.getMessage(ApplicationCode.FACE_MATCH));
            responseData.setWsResponse(null);
        }catch (ApplicationException e){
            responseData.setErrorCode(e.getCode());
            responseData.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
