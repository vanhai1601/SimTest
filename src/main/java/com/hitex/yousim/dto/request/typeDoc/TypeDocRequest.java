package com.hitex.yousim.dto.request.typeDoc;

import com.hitex.yousim.dto.request.IRequestData;
import lombok.Data;

@Data
public class TypeDocRequest  implements IRequestData {
	@Override
	public boolean isValid() {
		return false;
	}
}
