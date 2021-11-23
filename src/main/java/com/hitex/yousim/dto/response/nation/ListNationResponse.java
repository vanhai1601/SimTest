package com.hitex.yousim.dto.response.nation;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;
@Data
public class ListNationResponse implements IResponseData {
    private List<NationResponse> nationResponseList;
}
