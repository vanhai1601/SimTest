package com.hitex.yousim.dto.request.partner;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Partner;
import lombok.Data;

@Data
public class PartnerRequest extends Partner implements IRequestData {
	int page;
	int pageSize;
	@Override
	public boolean isValid() {
		return false;
	}
}
