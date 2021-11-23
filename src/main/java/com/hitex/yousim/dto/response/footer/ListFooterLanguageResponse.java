package com.hitex.yousim.dto.response.footer;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListFooterLanguageResponse implements IResponseData {
    List<FooterLanguageResponse> footerLanguageResponseList;
}

