package com.hitex.yousim.dto.response.servicepage;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListServiceLanguageResponse implements IResponseData {
    List<ServiceLanguageResponse> getListServiceLanguageResponse;
}
