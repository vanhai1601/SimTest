package com.hitex.yousim.dto.response;

import lombok.Data;

/**
 * BaseResponseData
 *
 * @author Chidq
 */
@Data
public class BaseResponseData<T extends IResponseData> {
    String errorCode;
    String message;
    T wsResponse;
}
