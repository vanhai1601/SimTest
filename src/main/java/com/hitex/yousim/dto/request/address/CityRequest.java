package com.hitex.yousim.dto.request.address;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.City;
import lombok.Data;

@Data
public class CityRequest extends City implements IRequestData {

	@Override
	public boolean isValid() {
		return false;
	}
}
