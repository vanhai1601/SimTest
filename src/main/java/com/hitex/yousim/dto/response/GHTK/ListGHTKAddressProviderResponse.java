package com.hitex.yousim.dto.response.GHTK;


import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListGHTKAddressProviderResponse implements IResponseData {
    List<GHTKAddressProviderResponse> ghtkAddressProviderResponseList;
}
