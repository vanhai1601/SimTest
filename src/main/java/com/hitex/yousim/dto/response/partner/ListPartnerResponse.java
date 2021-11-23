package com.hitex.yousim.dto.response.partner;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListPartnerResponse implements IResponseData {
	int totalItem;
	int totalPage;
	List<PartnerResponse> partnerResponseList;
}
