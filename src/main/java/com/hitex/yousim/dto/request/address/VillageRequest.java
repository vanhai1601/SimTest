package com.hitex.yousim.dto.request.address;

import com.hitex.yousim.dto.request.IRequestData;
import com.hitex.yousim.model.Village;
import lombok.Data;

@Data
public class VillageRequest extends Village implements IRequestData {

	@Override
	public boolean isValid() {
		return false;
	}
}
