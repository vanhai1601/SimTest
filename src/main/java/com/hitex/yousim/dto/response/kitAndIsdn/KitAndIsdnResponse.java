package com.hitex.yousim.dto.response.kitAndIsdn;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.view.KitAndIsdn;
import lombok.Data;

@Data
public class KitAndIsdnResponse extends KitAndIsdn implements IResponseData {
    private Double pricePromotion;
    private String descriptionPackage;
}
