package com.hitex.yousim.dto.response.upload;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.KIT;
import lombok.Data;

import java.util.List;

@Data
public class ListUploadKITRespone implements IResponseData {
    int totalItem;
    int totalPage;
    List<KIT> uploadRespones;
}
