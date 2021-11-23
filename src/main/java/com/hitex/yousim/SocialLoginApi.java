package com.hitex.yousim;

import com.google.gson.Gson;
import com.hitex.yousim.constant.ApplicationCode;
import com.hitex.yousim.dto.request.customer.SocialAccountInfo;
import com.hitex.yousim.dto.response.BaseResponseData;
import com.hitex.yousim.service.CustomerService;
import com.hitex.yousim.utils.exception.ApplicationException;
import java.io.IOException;
import lombok.Data;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class SocialLoginApi {
  @Value("${facebook.link.get.user.info}")
  private String FACEBOOK_LINK_GET_USER_INFO;
  @Value("${google.link.get.user.info}")
  private String GOOGLE_lINK_GET_USER_INFO;
  @Autowired
  private CustomerService customerService;

  public BaseResponseData excute(String accessToken, String provider) throws ApplicationException {
    BaseResponseData response = new BaseResponseData();
    try {
      switch (provider){
        case "FACEBOOK":{
          SocialAccountInfo accountInfo = getUserInfo(accessToken, FACEBOOK_LINK_GET_USER_INFO);
          accountInfo.setProvider("FACEBOOK");
          customerService.loginSocialAccount(accountInfo);
          response.setWsResponse(customerService.loginSocialAccount(accountInfo));
          return response;
        }
        case "GOOGLE":{
          SocialAccountInfo accountInfo = getUserInfo(accessToken, GOOGLE_lINK_GET_USER_INFO);
          accountInfo.setProvider("GOOGLE");
          customerService.loginSocialAccount(accountInfo);
          response.setWsResponse(customerService.loginSocialAccount(accountInfo));
          return response;
        }
        default:{
          response.setErrorCode("không hiểu login bằng facebook hay google");
          response.setMessage("không hiểu login bằng facebook hay google");
          return response;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      response.setErrorCode("Lỗi");
      response.setMessage("Lỗi");
      return response;
    }
  }

  public SocialAccountInfo getUserInfo(final String accessToken, String LINK_GET_USER_INFO) throws IOException{
    String link = String.format(LINK_GET_USER_INFO,accessToken);
    System.out.println(link);
    String response = Request.Get(link).execute().returnContent().asString();
    SocialAccountInfo socialAccountInfo = new Gson().fromJson(response, SocialAccountInfo.class);
    System.out.println(socialAccountInfo);
    return socialAccountInfo;
  }

}
