package com.hitex.yousim.dto.response.kit;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.KIT;
import lombok.Data;

import java.util.List;

@Data
public class KitRespone extends KIT implements IResponseData {
    List<Integer> listStatusKit;
}
