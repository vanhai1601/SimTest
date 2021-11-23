package com.hitex.yousim.dto.response.aboutUs;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListAboutUsAndLanguageResponse implements IResponseData {
    private List<ListAboutUsAndLanguageResponse> listAboutUsAndLanguageResponseList;
}
