package com.hitex.yousim.dto.response.user;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoResponse extends User implements IResponseData {
    private String cusName;
    private String address;
    private int sex;
    private Date dateOfBirth;
    private String idCard;


}
