package com.hitex.yousim.dto.request.inFoSub;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class InfoSubRequest implements IRequestData {
    private String msisdn;
    private String packageName;
    private String idCardFont;
    private String idCardBack;
    private String avatar;
    private String cusName;
    private String birthday;
    private String idCard;
    private String address;
    private String sex;
    private String addressCard;
    private String deliveryAdress;
    private String imageContract;
    private String typeDoc;
    private String pContractType;
    private String placeIssueCard;

    @Override
    public boolean isValid() {
        return false;
    }
}
