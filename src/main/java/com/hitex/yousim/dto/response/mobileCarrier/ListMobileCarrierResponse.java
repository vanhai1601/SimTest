package com.hitex.yousim.dto.response.mobileCarrier;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;
@Data
public class ListMobileCarrierResponse implements IResponseData {
    private List<MobileCarrierResponse> mobileCarrierResponseList;
}
