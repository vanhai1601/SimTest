package com.hitex.yousim.dto.response.kit;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class StatusRespone implements IResponseData {
    List<Integer> listStatusKit;
}
