package com.hitex.yousim.controller;

import com.hitex.yousim.constant.ApplicationCode;
import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.upload.FileRequest;
import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.dto.response.file.FileResponse;
import com.hitex.yousim.service.FileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/")
@Log4j2
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("uploadImg")
    public ResponseEntity uploadFileImg(@RequestBody BaseRequestData<FileRequest> requestData) throws IOException {
        BaseResponseData responseData = new BaseResponseData();
        try {
            FileRequest fileRequest = requestData.getWsRequest();
            FileResponse fileResponse =fileService.uploadFileImg(fileRequest);
            responseData.setWsResponse(fileResponse);
            responseData.setErrorCode(String.valueOf(ApplicationCode.SUCCESS));
            responseData.setMessage(ApplicationCode.getMessage(ApplicationCode.SUCCESS));
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }catch (Exception e){
            responseData.setErrorCode(String.valueOf(ApplicationCode.ERROR));
            responseData.setMessage(ApplicationCode.getMessage(ApplicationCode.ERROR));
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);

        }
    }
}
