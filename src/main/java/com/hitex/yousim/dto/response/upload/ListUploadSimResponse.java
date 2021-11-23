package com.hitex.yousim.dto.response.upload;

import com.hitex.yousim.dto.response.IResponseData;
import com.hitex.yousim.model.Sim;
import lombok.Data;

import java.util.List;

@Data
public class ListUploadSimResponse implements IResponseData {
    int totalItem;
    int totalPage;
    List<Sim> simList;
}
