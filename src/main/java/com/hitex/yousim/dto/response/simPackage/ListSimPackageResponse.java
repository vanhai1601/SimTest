package com.hitex.yousim.dto.response.simPackage;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListSimPackageResponse implements IResponseData {
    private int totalPage;
    private int totalItem;
    private List<SimPackageResponse> simPackageResponseList;
}
