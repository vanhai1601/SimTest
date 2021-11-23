package com.hitex.yousim.dto.response.physicType;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListPhysicTypeResponse implements IResponseData {
    private List<PhysicTypeResponse> physicTypeResponseList;
}
