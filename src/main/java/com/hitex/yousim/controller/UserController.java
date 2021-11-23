package com.hitex.yousim.controller;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.request.user.UserRequest;
import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.dto.response.user.UserInfoResponse;
import com.hitex.yousim.dto.response.user.UserRespone;
import com.hitex.yousim.model.User;
import com.hitex.yousim.service.UserService;
import com.hitex.yousim.utils.MessageUtils;
import com.hitex.yousim.utils.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;


@Controller
@RequestMapping("/api/")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @PostMapping(value = "addUser")
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserRespone userRespone = userService.addUser(requestData);
            return success(userRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "loginUser")
    @ResponseBody
    public ResponseEntity login(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserInfoResponse userRespone = userService.login(requestData);
            return success(userRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "detailUser")
    @ResponseBody
    public ResponseEntity detailUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserRespone userRespone = userService.detailUser(requestData);
            return success(userRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "updateUser")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserRespone userRespone = userService.updateUser(requestData);
            return  success(userRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "selfUpdatedUser")
    @ResponseBody
    public ResponseEntity selfUpdatedUser(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException {
        try {
            UserRespone userRespone = userService.selfUpdatedUser(requestData);
            return success(userRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }

    }

    @PostMapping(value = "forgotPassword")
    @ResponseBody
    public ResponseEntity forgotPassword(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException, MessagingException{
        try {
            UserRespone userRespone = userService.forgotPassword(requestData);
            return success(userRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

    @PostMapping(value = "createNewPassword")
    @ResponseBody
    public ResponseEntity createNewPassword(@RequestBody BaseRequestData<UserRequest> requestData) throws ApplicationException, MessagingException{
        try {
            UserRespone userRespone = userService.createNewPassword(requestData);
            return success(userRespone);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
