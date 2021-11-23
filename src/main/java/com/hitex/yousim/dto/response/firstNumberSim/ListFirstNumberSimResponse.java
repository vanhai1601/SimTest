package com.hitex.yousim.dto.response.firstNumberSim;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;
@Data
public class ListFirstNumberSimResponse implements IResponseData {
    private List<FirstNumberSimResponse> firstNumberSimResponseList;
}
