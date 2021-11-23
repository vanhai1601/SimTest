package com.hitex.yousim.dto.response.simPackage;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.view.ViewSimPackage;
import lombok.Data;

@Data
public class SimPackageResponse extends ViewSimPackage implements IResponseData {
    private double pricePromorion;
}
