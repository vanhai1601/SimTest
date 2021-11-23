package com.hitex.yousim.dto.response.aboutUs;

import com.hitex.yousim.dto.response.IResponseData;
import lombok.Data;

import java.util.List;

@Data
public class ListAboutUsLanguageResponse implements IResponseData {
    List<AboutUsAndLanguageResponse> aboutUsAndLanguageResponseList;
}
