package com.hitex.yousim.dto.response.address;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListDistrictResponse implements IResponseData {
	List<DistrictResponse> districtResponseList;
}
