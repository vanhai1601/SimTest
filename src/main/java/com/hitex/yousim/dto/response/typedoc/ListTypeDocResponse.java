package com.hitex.yousim.dto.response.typedoc;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListTypeDocResponse implements IResponseData {
	List<TypeDocResponse> typeDocResponseList;
}
