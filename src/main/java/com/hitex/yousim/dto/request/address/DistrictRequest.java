package com.hitex.yousim.dto.request.address;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.District;
import lombok.Data;

@Data
public class DistrictRequest extends District implements IRequestData {

	@Override
	public boolean isValid() {
		return false;
	}
}
