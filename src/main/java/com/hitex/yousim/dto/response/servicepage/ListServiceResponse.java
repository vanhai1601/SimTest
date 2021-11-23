package com.hitex.yousim.dto.response.servicepage;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListServiceResponse implements IResponseData {
    List<ServiceResponse> listServiceResponse;
}
