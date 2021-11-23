package com.hitex.yousim.dto.response.kit;
import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListKitRespone implements IResponseData {
    private int totalPage;
    private int totalItem;
    private List<KitRespone> kitDtos;

}
