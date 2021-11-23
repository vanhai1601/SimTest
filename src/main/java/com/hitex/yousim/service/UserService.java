package com.hitex.yousim.service;

import com.hitex.yousim.dto.request.BaseRequestData;
import com.hitex.yousim.dto.response.user.GetListUserReponse;
import com.hitex.yousim.dto.response.user.UserInfoResponse;
import com.hitex.yousim.dto.response.user.UserRespone;
import com.hitex.yousim.utils.exception.ApplicationException;

import javax.mail.MessagingException;

public interface UserService {
    UserRespone addUser(BaseRequestData userRequest) throws ApplicationException;
    UserInfoResponse login(BaseRequestData userRequest) throws ApplicationException;
    UserRespone updateUser(BaseRequestData userRequest) throws  ApplicationException;
    UserRespone selfUpdatedUser(BaseRequestData userRequest) throws  ApplicationException;
    UserRespone detailUser(BaseRequestData userRequest) throws  ApplicationException;
    GetListUserReponse getListUser(BaseRequestData userRequest) throws  ApplicationException;
    UserRespone getAdminInfo(BaseRequestData requestData) throws ApplicationException;
    boolean changePassUser(BaseRequestData userRequest) throws ApplicationException;
    boolean selfChangePassUser(BaseRequestData userRequest) throws ApplicationException;
    UserRespone forgotPassword(BaseRequestData userRequest) throws ApplicationException, MessagingException;
    UserRespone createNewPassword(BaseRequestData userRequest) throws ApplicationException;
}
